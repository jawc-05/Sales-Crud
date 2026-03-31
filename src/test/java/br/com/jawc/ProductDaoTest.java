/**
 * 
 */
package br.com.jawc;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.ProductDao;
import br.com.jawc.domain.Product;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author jawc
 *
 */
public class ProductDaoTest {
	
	private IProductDao produtoDao;

	private Product produto;
	
	public ProductDaoTest() {
		produtoDao = new ProductDao();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		produto = new Product();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoDao.cadastrar(produto);
	}
	
	@Test
	public void pesquisar() {
		Product produto = this.produtoDao.consultar(this.produto.getCodigo());
		Assert.assertNotNull(produto);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		produto.setCodigo("A2");
		Boolean retorno = produtoDao.cadastrar(produto);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluir() {
		produtoDao.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		produto.setNome("Rodrigo Pires");
		produtoDao.alterar(produto);
		
		Assert.assertEquals("Rodrigo Pires", produto.getNome());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Product> list = produtoDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
