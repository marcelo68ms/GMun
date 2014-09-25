package br.com.sw2.comercial.service;

import br.com.sw2.comercial.bean.Fornecedor;
import br.com.sw2.comercial.dao.BaseDAO;

public class FornecedorService {

	/**
	 * Criar um novo Fornecedor
	 * @param Fornecedor
	 */
	public void criarFornecedor(Fornecedor fornecedor) {
		obterDAO().inserir(fornecedor);
	}

	/**
	 * Recuperar um fornecedor a partir de seu CNPJ
	 * @param cnpj
	 * @return Fornecedor
	 */
	public Fornecedor recuperaFornecedorById(String cnpj) {
		return obterDAO().getEntityById(cnpj);
	}
	
	/**
	 * Remover um fornecedor a partir de seu id
	 * @param cnpj
	 */
	public void removerFornecedor(String cnpj) {
		obterDAO().apagar(cnpj);
	}

	/**
	 * Alterar o cadastro de um fornecedor
	 * @param Fornecedor
	 */
	public void alterarFornecedor(Fornecedor fornecedor) {
		obterDAO().gravar(fornecedor);
	}

	private BaseDAO<Fornecedor> obterDAO() {
		return new BaseDAO<Fornecedor>(Fornecedor.class);
	}
	
}
