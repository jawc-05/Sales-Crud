/**
 * 
 */
package br.com.jawc;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import br.com.jawc.dao.IClientDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author jawc-05
 *
 */
public class ClientDaoTest {
	
	private IClientDao clientDao;

	private Client client;
	
	public ClientDaoTest() {
		clientDao = new ClientDao();
	}
	
	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		client = new Client();
		client.setCpf("12312312312");
		client.setNome("João");
		client.setCidade("Lublin");
		client.setEnd("End");
		client.setEstado("Lubelskie");
		client.setNumero(10);
		client.setTel(1199999999L);
		clientDao.cadastrar(client);
	}
	
	@Test
	public void pesquisarCliente() {
		Client clienteConsultado = clientDao.consultar(client.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		client.setCpf("56565656565");
		Boolean ans = clientDao.cadastrar(client);
		Assert.assertTrue(ans);
	}
	
	
	@Test
	public void excluirCliente() {
		clientDao.excluir(client.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		client.setNome("João Alfredo");
		clientDao.alterar(client);
		Assert.assertEquals("João Alfredo", client.getNome());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Client> list = clientDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
