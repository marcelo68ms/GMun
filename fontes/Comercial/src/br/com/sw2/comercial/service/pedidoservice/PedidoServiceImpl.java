package br.com.sw2.comercial.service.pedidoservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.sw2.comercial.bean.Fornecedor;
import br.com.sw2.comercial.bean.ItemOrcamento;
import br.com.sw2.comercial.bean.ItemPedido;
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
		
		return gerarOrcamento(pedido, nrcnpj);
	}

	private Orcamento gerarOrcamento(Pedido pedido, String nrcnpj) {
		// TODO - Codigo somente para testar a comunicacao, posteriomente precisa alterar para 
		// a logica correta de caculo de orcamento 
		Orcamento orcamento = new Orcamento(pedido.getNrpedido(), nrcnpj);
		
		orcamento.setDtenvio(new Date());
		orcamento.setPedido(pedido);
		
		List<ItemOrcamento> itensOrcamento = new ArrayList<ItemOrcamento>();
		for (ItemPedido itemPedido : pedido.getItemPedidoList()) {
			ItemOrcamento itemOrcamento = new ItemOrcamento();
			itemOrcamento.setMaterial(itemPedido.getMaterial());
			itemOrcamento.setVlmaterial(new BigDecimal(100));
			itemOrcamento.setOrcamento(orcamento);
			itensOrcamento.add(itemOrcamento);
		}
		
		orcamento.setItemOrcamentoList(itensOrcamento);
		
		FornecedorService fornecedorService = new FornecedorService();
		Fornecedor fornecedor = fornecedorService.recuperaFornecedorById(nrcnpj);
		
		orcamento.setFornecedor(fornecedor);
		
		if (fornecedor.getOrcamentoList() == null) {
			fornecedor.setOrcamentoList(new ArrayList<Orcamento>());
		}
		
		fornecedor.getOrcamentoList().add(orcamento);
		
		pedido.setOrcamentoList(new ArrayList<Orcamento>());
		pedido.getOrcamentoList().add(orcamento);
		
		BaseDAO<Orcamento> orcamentoDAO = new BaseDAO<Orcamento>(Orcamento.class);
		orcamentoDAO.inserir(orcamento);
		return orcamento;
	}


}
