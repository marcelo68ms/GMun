package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import br.com.sw2.comercial.bean.Fornecedor;
import br.com.sw2.comercial.service.FornecedorService;

public class FornecedorServiceTest {
	
	private static final String RAZAO_SOCIAL = "Fornecedor Teste";
	private static final String CNPJ = "123";
	FornecedorService service = new FornecedorService();
	
	@After
	public void tearDown() {
		service.removerFornecedor(CNPJ);
	}

	@Test
	public void InserirNovoFornecedor() {
		Fornecedor fornecedor = new Fornecedor(CNPJ, RAZAO_SOCIAL);
		
		try {
			service.criarFornecedor(fornecedor);
		} catch (Exception e){
			fail("Deu erro na criação");
		}
		
		Fornecedor fornecedorDB = service.recuperaFornecedorById(CNPJ);
		assertNotNull(fornecedorDB);
		assertEquals(RAZAO_SOCIAL, fornecedorDB.getNmrazaosocial());
	}
	
	@Test
	public void AlterarForncededor() {
		Fornecedor fornecedor = new Fornecedor(CNPJ, RAZAO_SOCIAL);
		
		try {
			service.criarFornecedor(fornecedor);
		} catch (Exception e){
			fail("Deu erro na criação");
		}
		
		fornecedor.setNmrazaosocial("Alterado");
		fornecedor.setDscidade("Campinas");
		
		try {
			service.alterarFornecedor(fornecedor);
		} catch (Exception e) {
			fail("Deu erro na alteração");
		}
		
		Fornecedor fornecedorDB = service.recuperaFornecedorById(CNPJ);
		assertNotNull(fornecedorDB);
		assertEquals("Alterado", fornecedorDB.getNmrazaosocial());
		assertEquals("Campinas", fornecedorDB.getDscidade());
		
	}

}
