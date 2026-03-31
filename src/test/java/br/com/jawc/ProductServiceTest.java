/**
 * 
 */
package br.com.jawc;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.ProductDaoMock;
import br.com.jawc.domain.Product;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;
import br.com.jawc.services.ProductService;
import br.com.jawc.services.ProductService;

/**
 * @author jawc
 *
 */
public class ProductServiceTest {

	private ProductService productService;
	
	private Product product;
	
	public ProductServiceTest() {
		IProductDao dao = new ProductDaoMock();
		productService = new ProductService(dao);
	}
	
	@Before
	public void init() {
		product = new Product();
		product.setCodigo("A1");
		product.setDescricao("Product 1");
		product.setNome("Product 1");
		product.setValor(BigDecimal.TEN);
	}
	
	@Test
	public void pesquisar() {
		Product productr = this.productService.consultar(product.getCodigo());
		Assert.assertNotNull(productr);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		Boolean retorno = productService.cadastrar(product);
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluir() {
		productService.excluir(product.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		product.setNome("João Alfredo");
		productService.alterar(product);
		
		Assert.assertEquals("João Alfredo", product.getNome());
	}
}
