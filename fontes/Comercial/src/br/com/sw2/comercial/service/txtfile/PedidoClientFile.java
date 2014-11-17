package br.com.sw2.comercial.service.txtfile;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.comercial.service.email.EmailService;

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
		
		EmailService.sendSimpleMail("Arquivo de Pedido gerado", "Foi criado um arquivo de pedido", "odontodo@gmail.com");
		
	}
	
	/* Formato do Arquivo
	 * [CNPJ Fornecedor]
	 * [Item 1: Quantidade|Cod Material ]  
	 * [Item 2: Quantidade|Cod Material ]  
	 */

}
