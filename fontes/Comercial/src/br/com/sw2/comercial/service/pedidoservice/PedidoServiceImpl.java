package br.com.sw2.comercial.service.pedidoservice;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.sw2.comercial.bean.Fornecedor;
import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.Pedido;
import br.com.sw2.comercial.dao.BaseDAO;
import br.com.sw2.comercial.service.FornecedorService;

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
	public Orcamento fazerPedido(Pedido pedido, String nrcnpj) {
		
		BaseDAO<Pedido> pedidoDAO = new BaseDAO<Pedido>(Pedido.class);
		pedidoDAO.inserir(pedido);
		
		// TODO - Codigo somente para testar a comunicacao, posteriomente precisa alterar para 
		// a logica correta de caculo de orcamento e fornecedor
		Orcamento orcamento = new Orcamento(pedido.getNrpedido(), nrcnpj);
		
		orcamento.setPedido(pedido);
		pedido.setOrcamentoList(new ArrayList<Orcamento>());
		pedido.getOrcamentoList().add(orcamento);
		
		FornecedorService fornecedorService = new FornecedorService();
		Fornecedor fornecedor = fornecedorService.recuperaFornecedorById(nrcnpj);
		
		orcamento.setFornecedor(fornecedor);
		
		if (fornecedor.getOrcamentoList() == null) {
			fornecedor.setOrcamentoList(new ArrayList<Orcamento>());
		}
		
		fornecedor.getOrcamentoList().add(orcamento);
		
		BaseDAO<Orcamento> orcamentoDAO = new BaseDAO<Orcamento>(Orcamento.class);
		orcamentoDAO.inserir(orcamento);
		
		return orcamento;
	}


}
