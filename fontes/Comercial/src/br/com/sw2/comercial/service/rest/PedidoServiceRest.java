package br.com.sw2.comercial.service.rest;

import javax.ws.rs.core.Response;

import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.Pedido;
import br.com.sw2.comercial.dao.BaseDAO;
import br.com.sw2.comercial.service.pedidoservice.PedidoServiceImpl;

public class PedidoServiceRest implements IPedidoServiceRest {

	public Response fazerPedido(String cnpj, Pedido pedido) {
		PedidoServiceImpl service = new PedidoServiceImpl();
		Orcamento orcamento = service.fazerPedido(pedido, cnpj);

		if (orcamento != null) {
			return Response.ok(orcamento).build();
		}
		
		return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();

	}

	public Response recuperarPedido(Integer nrpedido) {

		BaseDAO<Pedido> pedidoDAO = new BaseDAO<Pedido>(Pedido.class);
		Pedido pedido = pedidoDAO.getEntityById(nrpedido);
		
		if (pedido != null) {
			return Response.ok(pedido).build();
		}
		
		return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();

	}
}
