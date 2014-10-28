package br.com.sw2.comercial.service.txtfile;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sw2.comercial.bean.ItemOrcamento;
import br.com.sw2.comercial.bean.ItemPedido;
import br.com.sw2.comercial.bean.Material;
import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.Pedido;
import br.com.sw2.comercial.dao.BaseDAO;
import br.com.sw2.comercial.service.pedidoservice.PedidoServiceImpl;

public class PedidoServiceFile {

	private static final String FORMAT_DATE = "yyyyMMddHHmmss";
	private static final String DELIMITER = "|";
	private static FileOperation fileOp = new FileOperation();

	public static void main(String[] args) {
		
		ChecarNovosPedidos pedidosThread = new ChecarNovosPedidos();
		pedidosThread.run();

/*		WatchKey key = null;

		while (true) {
			try {
				key = fileOp.getWatchService().take();
				for (WatchEvent<?> event : key.pollEvents()) {
					Path arquivo = (Path) event.context();
					tratarArquivo(arquivo);
				}
				key.reset();
			} catch (InterruptedException e) {
				System.err.printf(
						"Erro ao criar observador de diretorio: %s.\n",
						e.getMessage());
			}
		}*/

	}

	public void tratarArquivo(Path arquivo) {
		String nome = arquivo.getFileName().toString();
		Pedido pedido = new Pedido();
		pedido.setDspedido(recuperarDataPedido(nome));

		// TODO Presume-se que o arquivo venha com o conteúdo correto, nenhum
		// tipo de validação está sendo feito, visto que é uma POC.
		List<String> conteudo = fileOp.lerArquivo(nome);

		// A primera informação do arquivo deve ser o CNPJ
		String cnpj = conteudo.get(0);
		conteudo.remove(0);

		recuperarItensPedido(pedido, conteudo);

		PedidoServiceImpl service = new PedidoServiceImpl();
		Orcamento orcamento = service.fazerPedido(pedido, cnpj);

		List<String> linhas = orcamentoToList(orcamento);
		String nrPedido = orcamento.getPedido().getNrpedido().toString();
		String nomeArquivo = "Orcamento" + nrPedido + "-";
		fileOp.criarArquivo(nomeArquivo, linhas);
		fileOp.inlcuirNumPedidoArquivo(arquivo, nrPedido);

		System.out.println("===> Orcamento: " + linhas);

	}

	private void recuperarItensPedido(Pedido pedido,
			List<String> conteudo) {
		List<ItemPedido> itens = new ArrayList<ItemPedido>();

		for (String linha : conteudo) {
			// Recupera quantidade
			String[] linhaItem = linha.split("\\|");
			ItemPedido item = new ItemPedido();
			item.setNrquant(new BigDecimal(linhaItem[0]));

			// Recupera o material
			BaseDAO<Material> materialDAO = new BaseDAO<Material>(
					Material.class);
			Material material = materialDAO.getEntityById(linhaItem[1]);
			item.setMaterial(material);
			material.getItemPedidoList().add(item);

			item.setPedido(pedido);

			itens.add(item);
		}

		pedido.setItemPedidoList(itens);
	}

	private Date recuperarDataPedido(String nome) {
		// Recuperar data do pedido, que faz parte do nome do arquivo, que
		// deve seguir o modelo "pedidoyyyyMMddhhmmss.txt"
		SimpleDateFormat formatoData = new SimpleDateFormat(FORMAT_DATE);
		Date dataPedido = null;
		try {
			dataPedido = formatoData.parse(nome.substring(6, 20));
		} catch (ParseException e) {
			System.err.printf("Erro ao recuperar a data do pedido: %s.\n",
					e.getMessage());
		}
		return dataPedido;
	}

	/*
	 * Formato arquivo 
	 * [Numero Pedido]|[Data Envio]|[CNPJ Fornecedor] 
	 * [Item 1: Cod Material]|[Item 1:Valor] 
	 * [Item 2: Cod Material]|[Item 2:Valor]
	 */
	private List<String> orcamentoToList(Orcamento orcamento) {
		List<String> conteudo = new ArrayList<String>();

		conteudo.add(orcamento.getPedido().getNrpedido().toString() + DELIMITER + 
						orcamento.getDtenvio().toString() + DELIMITER + 
						orcamento.getFornecedor().getNrcnpj());

		for (ItemOrcamento item : orcamento.getItemOrcamentoList()) {
			conteudo.add(item.getMaterial().getCdmaterial() + DELIMITER + item.getVlmaterial().toString());
		}

		return conteudo;
	}

}
