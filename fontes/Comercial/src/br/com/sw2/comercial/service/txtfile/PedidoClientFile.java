package br.com.sw2.comercial.service.txtfile;

import java.util.ArrayList;
import java.util.List;

public class PedidoClientFile {
	
	public static void main(String[] args) {
		FileOperation fileOp = new FileOperation();
		List<String> linhas = new ArrayList<String>();
		String linha = "12345678910";
		linhas.add(linha);
		linha = "7|MatTestJunit";
		linhas.add(linha);
		linha = "15|MatTest01";
		linhas.add(linha);
		fileOp.criarArquivo("Pedido", linhas);
	}
	
	/* Formato do Arquivo
	 * [CNPJ Fornecedor]
	 * [Item 1: Quantidade|Cod Material ]  
	 * [Item 2: Quantidade|Cod Material ]  
	 */

}
