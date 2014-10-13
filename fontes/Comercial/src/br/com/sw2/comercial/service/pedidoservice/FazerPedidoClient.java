package br.com.sw2.comercial.service.pedidoservice;

import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.Pedido;

public class FazerPedidoClient {
	
	// OBS: PAra funcionar é preciso ter um fornecedor cadastrado com este cnpj
	private static final String CNPJ_FAKE = "12345678910";

	public static void main(String[] args) {
		
		//Inicia a fábrica dos proxies
		PedidoService_Service pedidoServiceFactory = new PedidoService_Service();
		
		//Obtém um proxy
		PedidoService service = pedidoServiceFactory.getPedidoServiceSOAP();
		
		//Executa o método remoto
		Pedido pedido = new Pedido();
		Orcamento orcamento = service.fazerPedido(pedido, CNPJ_FAKE);
		System.out.println("Orcamento: " + orcamento.toString());
		
	}

}
