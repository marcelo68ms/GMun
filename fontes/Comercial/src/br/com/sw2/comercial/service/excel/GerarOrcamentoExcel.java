package br.com.sw2.comercial.service.excel;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import br.com.sw2.comercial.bean.ItemOrcamento;
import br.com.sw2.comercial.bean.ItemPedido;
import br.com.sw2.comercial.bean.Material;
import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.Pedido;
import br.com.sw2.comercial.service.pedidoservice.PedidoServiceImpl;
import br.com.sw2.comercial.service.txtfile.PedidoServiceFile;

public class GerarOrcamentoExcel {
	
	private static final String ORCAMENTO = "Orcamento";
	private static ExcelUtils utils = new ExcelUtils();
	
	public static void main(String[] args) {
		
		ChecarNovosPedidos thread = new ChecarNovosPedidos();
		thread.run();
		
	}

	public void tratarArquivo(Path arquivo) {
		try {
			Workbook planilha = Workbook.getWorkbook(arquivo.toFile());
			Sheet aba = planilha.getSheet(0);
			
			// Recuperar CNPJ
			Cell a2 = aba.getCell(0,1); 
			String cnpj = a2.getContents();
			
			Pedido pedido = recuperarItensPedido(arquivo, aba);
			
			planilha.close();
			
			PedidoServiceImpl service = new PedidoServiceImpl();
			Orcamento orcamento = service.fazerPedido(pedido, cnpj);
			gerarPlanilhaOrcamento(orcamento);
			renomearPlanilhaPedido(arquivo, pedido.getNrpedido().toString());
			
		} catch (BiffException | IOException e) {
			System.err.printf("Erro ao ler planilha: %s.\n", e.getMessage());
		}
	}

	private void gerarPlanilhaOrcamento(Orcamento orcamento) {

		Path arquivo = utils.gerarPath(ORCAMENTO + orcamento.getPedido().getNrpedido().toString() + "-");
		
		try {
			WritableWorkbook planilha = Workbook.createWorkbook(arquivo.toFile());
			WritableSheet aba = planilha.createSheet(ORCAMENTO, 0);	
			
			// Definir tamanho maior para algumas colunas
			aba.setColumnView(1, 15);
			aba.setColumnView(2, 12);
			aba.setColumnView(3, 11);
			
			utils.addLabel(aba, 0, 0, "Pedido");
			utils.addLabel(aba, 1, 0, "Data Envio");
			utils.addLabel(aba, 2, 0, "Fornecedor");
			utils.addLabel(aba, 3, 0, "Material");
			utils.addLabel(aba, 4, 0, "Valor");
			
			utils.addNumber(aba, 0, 1, orcamento.getPedido().getNrpedido().doubleValue());
			utils.addDate(aba, 1, 1, orcamento.getDtenvio());
			utils.addLabel(aba, 2, 1, orcamento.getFornecedor().getNrcnpj());

			List<ItemOrcamento> itens = orcamento.getItemOrcamentoList();
			for (ItemOrcamento item : itens) {
				int row = itens.indexOf(item) + 1;
				utils.addLabel(aba, 3, row, item.getMaterial().getCdmaterial());
				utils.addNumber(aba, 4, row, item.getVlmaterial().doubleValue());
			}
			
			planilha.write(); 
			planilha.close();
			
		} catch (IOException | WriteException e) {
			System.err.printf("Erro ao criar planilha: %s.\n", e.getMessage());
		}
			
	}

	private Pedido recuperarItensPedido(Path arquivo, Sheet aba) {
		Pedido pedido  = new Pedido();
		List<ItemPedido> itens = new ArrayList<ItemPedido>();
		
		for (int row = 1; row < aba.getRows(); row++) {
			
			// Cod Material
			LabelCell celula = (LabelCell) aba.getCell(1, row);	
			Material material = new Material(celula.getContents());
			
			ItemPedido item = new ItemPedido();
			item.setMaterial(material);
			
			// Qtde
			NumberCell celulaNum = (NumberCell) aba.getCell(2, row);
			item.setNrquant(new BigDecimal(celulaNum.getValue()));
			
			item.setPedido(pedido);
			
			itens.add(item);
		}
		
		PedidoServiceFile fileService = new PedidoServiceFile();
		Date data = fileService.recuperarDataPedido(arquivo.getFileName().toString());
		
		pedido.setDspedido(data);
		pedido.setItemPedidoList(itens);
		return pedido;
	}
	
	public DirectoryStream<Path> recuperarArquivosPedido() {
		
		Path dir = Paths.get(ExcelUtils.PATH);
		String filtro = "Pedido*.xls";

		DirectoryStream<Path> stream = null;
		try {
			stream = Files.newDirectoryStream(dir, filtro);
		} catch (IOException e) {
			System.err.printf("Erro ao listar planilhas: %s.\n", e.getMessage());
		}

		return stream;
		
	}
	
	private void renomearPlanilhaPedido(Path arquivo, String nrPedido) {
		try {
			String nomeAtual = arquivo.getFileName().toString();
			String novoNome = nomeAtual.replace("Pedido", "NrPedido" + nrPedido + "-");
			Files.move(arquivo, arquivo.resolveSibling(novoNome));
		} catch (IOException e) {
			System.err.printf("Erro ao renomear planilha: %s.\n", e.getMessage());
		}
		
	}

}
