/**
 * 
 */
package br.com.jawc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.jawc.dao.ClientDaoMock;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;
import br.com.jawc.services.ClientService;
import br.com.jawc.services.IClientService;

/**
 * @author jawc
 *
 */
public class ClientServiceTest {
	
	private IClientService clientService;
	
	private Client client;
	
	public ClientServiceTest() {
		IClientDao dao = new ClientDaoMock();
		clientService = new ClientService(dao);
	}
	
	@Before
	public void init() {
		client = new Client();
		client.setCpf("132413434");
		client.setNome("João Alfredo");
		client.setCidade("Lublin");
		client.setEnd("End");
		client.setEstado("Lubelskie");
		client.setNumero(10);
		client.setTel(1199999999L);
		
	}
	
	@Test
	public void pesquisarCliente() {
		Client searchedClient = clientService.searchByCpf(client.getCpf());
		Assert.assertNotNull(searchedClient);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		Boolean retorno = clientService.cadastrar(client);
		
		Assert.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clientService.delete(client.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		client.setNome("Alfredo João");
		clientService.update(client);
		
		Assert.assertEquals("Alfredo João", client.getNome());
	}
}
