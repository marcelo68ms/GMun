package br.com.sw2.comercial.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sw2.comercial.bean.Pedido;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_XML)
@Consumes(MediaType.APPLICATION_XML)
public interface IPedidoServiceRest {
	
	@POST
	@Path("/fornecedor/{id}")
	public Response fazerPedido(@PathParam("id") String cnpj, Pedido pedido);
	
	@GET
	@Path("/{id}")
	public Response recuperarPedido(@PathParam("id") Integer nrpedido);

}
