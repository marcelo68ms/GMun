package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.sw2.comercial.bean.Fornecedor;
import br.com.sw2.comercial.bean.Orcamento;
import br.com.sw2.comercial.bean.OrcamentoPK;
import br.com.sw2.comercial.bean.Pedido;
import br.com.sw2.comercial.dao.BaseDAO;
import br.com.sw2.comercial.service.pedidoservice.PedidoServiceImpl;

public class PedidoServiceTest {
	
	BaseDAO<Pedido> pedidoDAO = new BaseDAO<Pedido>(Pedido.class);
	BaseDAO<Fornecedor> fornecedorDAO = new BaseDAO<Fornecedor>(Fornecedor.class);
	BaseDAO<Orcamento> orcamentoDAO = new BaseDAO<Orcamento>(Orcamento.class);
	
	private static final String CNPJ = "11111111000111";
	private Integer idPedido;

	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		// Apagar Orcamento
		Orcamento orcamentoDB = orcamentoDAO.getEntityById(new OrcamentoPK(idPedido, CNPJ));
		orcamentoDAO.apagar(orcamentoDB);
		
		// Apagar Pedido
		Pedido pedidoDB = pedidoDAO.getEntityById(idPedido);
		pedidoDAO.apagar(pedidoDB);
				
		// Apagar fornecedor
		Fornecedor fornecedor = fornecedorDAO.getEntityById(CNPJ);
		fornecedorDAO.apagar(fornecedor);
	}
	
	@Test
	public void FazerPedidotest() {
		
		Pedido pedido = new Pedido();
		pedido.setDspedido(new Date());
		
		// Criar Fornecedor
		Fornecedor fornecedor = new Fornecedor(CNPJ,"FornecedorTestJUnit");
		fornecedorDAO.inserir(fornecedor);
		
		PedidoServiceImpl service = new PedidoServiceImpl();
		try {
			Orcamento orcamento = service.fazerPedido(pedido, fornecedor);
			assertNotNull(orcamento);
			assertEquals(pedido.getNrpedido(), orcamento.getPedido().getNrpedido());
			assertEquals(CNPJ, orcamento.getFornecedor().getNrcnpj());
		} catch (Exception e){
			fail("Erro ao fazer pedido");
		}
		
		idPedido = pedido.getNrpedido();
		
	}

}
