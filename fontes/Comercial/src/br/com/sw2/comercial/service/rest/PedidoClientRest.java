package br.com.sw2.comercial.service.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import br.com.sw2.comercial.bean.Pedido;

public class PedidoClientRest {
	
	private static final String URL = "http://localhost:8080/Comercial/pedidos/fornecedor/12345678910";

	public static void main(String[] args) {
	
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(URL);
		
		Pedido pedido = new Pedido();

		Response response = target.request().post(Entity.entity(pedido, MediaType.APPLICATION_XML));
		
		System.out.println(response.getStatus());
		String value = response.readEntity(String.class);
        System.out.println(value);	    
	}
	
}
