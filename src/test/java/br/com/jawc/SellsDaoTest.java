/**
 * 
 */
package br.com.jawc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;

import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.ISellsDao;
import br.com.jawc.dao.ProductDao;
import br.com.jawc.dao.SellsDao;
import br.com.jawc.domain.Client;
import br.com.jawc.domain.Product;
import br.com.jawc.domain.Sells;
import br.com.jawc.domain.Sells.Status;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author jawc
 *
 */
public class SellsDaoTest {
	
	private ISellsDao sellsDao;
	
	private IClientDao clientDao;
	
	private IProductDao productDao;

	//private Sells sell;
	
	private Client client;
	
	private Product product;
	
	public SellsDaoTest() {
		sellsDao = new SellsDao();
		clientDao = new ClientDao();
		productDao = new ProductDao();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		this.client = cadastrarClient();
		this.product = cadastrarProduct("A1", BigDecimal.TEN);
	}

	
	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException {
		Sells sell = criarSells("A1");
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		Sells sellConsultada = sellsDao.consultar(sell.getCodigo());
		assertNotNull(sellConsultada);
		assertEquals(sell.getCodigo(), sellConsultada.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		Sells sell = criarSells("A2");
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	
	@Test
	public void cancelarSells() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A3";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		sell.setStatus(Status.CANCELADA);
		sellsDao.alterar(sell);
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		assertEquals(codigoSells, sellConsultada.getCodigo());
		assertEquals(Status.CANCELADA, sellConsultada.getStatus());
	}
	
	@Test
	public void adicionarMaisProductsDoMesmo() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A4";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		sellConsultada.adicionarProduto(product, 1);
		
		assertTrue(sell.getQuantidadeTotalProdutos() == 3);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(30)));
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void adicionarMaisProductsDiferentes() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A5";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		Product prod = cadastrarProduct(codigoSells, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoSells, prod.getCodigo());
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		sellConsultada.adicionarProduto(prod, 1);
		
		assertTrue(sell.getQuantidadeTotalProdutos() == 3);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(70)));
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void salvarProductExistente() throws TipoChaveNaoEncontradaException {
		Sells sell = criarSells("A6");
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
	
		Boolean retorno1 = sellsDao.cadastrar(sell);
		assertFalse(retorno1);
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void removerProduct() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A7";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		Product prod = cadastrarProduct(codigoSells, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoSells, prod.getCodigo());
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		sellConsultada.adicionarProduto(prod, 1);
		assertTrue(sell.getQuantidadeTotalProdutos() == 3);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		
		sellConsultada.removerProduto(prod, 1);
		assertTrue(sell.getQuantidadeTotalProdutos() == 2);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void removerApenasUmProduct() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A8";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		Product prod = cadastrarProduct(codigoSells, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoSells, prod.getCodigo());
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		sellConsultada.adicionarProduto(prod, 1);
		assertTrue(sell.getQuantidadeTotalProdutos() == 3);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		
		sellConsultada.removerProduto(prod, 1);
		assertTrue(sell.getQuantidadeTotalProdutos() == 2);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void removerTodosProducts() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A9";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		Product prod = cadastrarProduct(codigoSells, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoSells, prod.getCodigo());
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		sellConsultada.adicionarProduto(prod, 1);
		assertTrue(sell.getQuantidadeTotalProdutos() == 3);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		
		sellConsultada.removerTodosProdutos();
		assertTrue(sell.getQuantidadeTotalProdutos() == 0);
		assertTrue(sell.getValorTotal().equals(BigDecimal.valueOf(0)));
		assertTrue(sell.getStatus().equals(Status.INICIADA));
	} 
	
	@Test
	public void finalizarSells() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A10";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		sellsDao.finalizarSell(sell);
		
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		assertEquals(sell.getCodigo(), sellConsultada.getCodigo());
		assertEquals(sell.getStatus(), sellConsultada.getStatus());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void tentarAdicionarProductsSellsFinalizada() throws TipoChaveNaoEncontradaException {
		String codigoSells = "A11";
		Sells sell = criarSells(codigoSells);
		Boolean retorno = sellsDao.cadastrar(sell);
		assertTrue(retorno);
		assertNotNull(sell);
		assertEquals(codigoSells, sell.getCodigo());
		
		sellsDao.finalizarSell(sell);
		Sells sellConsultada = sellsDao.consultar(codigoSells);
		assertEquals(sell.getCodigo(), sellConsultada.getCodigo());
		assertEquals(sell.getStatus(), sellConsultada.getStatus());

		sellConsultada.adicionarProduto(this.product, 1);
		
	}

	private Product cadastrarProduct(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException {
		Product product = new Product();
		SellsDaoTest.this.product.setCodigo(codigo);
		SellsDaoTest.this.product.setDescricao("Product 1");
		SellsDaoTest.this.product.setNome("Product 1");
		SellsDaoTest.this.product.setValor(valor);
		SellsDaoTest.this.productDao.cadastrar(SellsDaoTest.this.product);
		return SellsDaoTest.this.product;
	}

	private Client cadastrarClient() throws TipoChaveNaoEncontradaException {
		Client client = new Client();
		client.setCpf("1111111");
		client.setNome("João");
		client.setCidade("Lublin");
		client.setEnd("End");
		client.setEstado("Lubelskie");
		client.setNumero(10);
		client.setTel(1199999999L);
		clientDao.cadastrar(client);
		return client;
	}
	
	private Sells criarSells(String codigo) {
		Sells sell = new Sells();
		sell.setCodigo(codigo);
		sell.setDataVenda(Instant.now());
		sell.setClient(this.client);
		sell.setStatus(Status.INICIADA);
		sell.adicionarProduto(this.product, 2);
		return sell;
	}

}
