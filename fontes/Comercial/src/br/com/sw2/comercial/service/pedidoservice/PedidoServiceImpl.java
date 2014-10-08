package br.com.sw2.comercial.service.pedidoservice;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.sw2.comercial.bean.Fornecedor;
import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.Pedido;
import br.com.sw2.comercial.dao.BaseDAO;

@WebService(endpointInterface = "br.com.sw2.comercial.service.pedidoservice.PedidoService", 
	portName = "PedidoServiceSOAP", serviceName = "PedidoService", 
	targetNamespace = "http://www.sw2.com.br/comercial/service/PedidoService", 
	wsdlLocation = "WEB-INF/contracts/PedidoService.wsdl")
public class PedidoServiceImpl implements PedidoService {

	

	@WebMethod(action = "PedidoService/FazerPedido")
	@WebResult(name = "orcamento", targetNamespace = "")
	@RequestWrapper(localName = "fazerPedido", 
		targetNamespace = "http://www.sw2.com.br/comercial/service/PedidoService",
		className ="br.com.sw2.comercial.service.pedidoservice")
	@ResponseWrapper(localName = "fazerPedidoResponse",
		targetNamespace = "http://www.sw2.com.br/comercial/service/PedidoService",
		className = "br.com.sw2.comercial.service.pedidoservice")
	public Orcamento fazerPedido(Pedido pedido, Fornecedor fornecedor) {
		
		BaseDAO<Pedido> pedidoDAO = new BaseDAO<Pedido>(Pedido.class);
		pedidoDAO.inserir(pedido);
		
		// TODO - Codigo somente para testar a comunicacao, posteriomente precisa alterar para 
		// a logica correta de caculo de orcamento e fornecedor
		Orcamento orcamento = new Orcamento(pedido.getNrpedido(), fornecedor.getNrcnpj());
		
		orcamento.setPedido(pedido);
		pedido.getOrcamentoList().add(orcamento);
		
		orcamento.setFornecedor(fornecedor);
		fornecedor.getOrcamentoList().add(orcamento);
		
		BaseDAO<Orcamento> orcamentoDAO = new BaseDAO<Orcamento>(Orcamento.class);
		orcamentoDAO.inserir(orcamento);
		
		return orcamento;
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/comercial/service/PedidoService", new PedidoServiceImpl());
		System.out.println("Serviço inicializado!");
	}
	
}
