package br.com.sw2.comercial.service.excel;


import java.io.IOException;
import java.nio.file.Path;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class GerarPedidoExcel {
	
	private static final String CNPJ = "12345678910";
	private static final String PEDIDO = "Pedido";
	private static ExcelUtils utils = new ExcelUtils();

	public static void main(String[] args) {
		
		Path arquivo = utils.gerarPath(PEDIDO);
		
		try {
			WritableWorkbook planilha = Workbook.createWorkbook(arquivo.toFile());
			WritableSheet aba = planilha.createSheet(PEDIDO, 0);
			
			utils.addLabel(aba, 0, 0, "Fornecedor"); 
			utils.addLabel(aba, 1, 0, "Material");
			utils.addLabel(aba, 2, 0, "Quantidade");
			
			utils.addLabel(aba, 0, 1, CNPJ);
			utils.addLabel(aba, 1, 1, "MatTestJunit");
			utils.addNumber(aba, 2, 1, 1000d);
			utils.addLabel(aba, 1, 2, "MatTest01");
			utils.addNumber(aba, 2, 2, 1400d);
			
			planilha.write(); 
			planilha.close();
			
		} catch (IOException | WriteException e) {
			System.err.printf("Erro na criacao da planilha: %s.\n", e.getMessage());
		}
		
		
	}


}
