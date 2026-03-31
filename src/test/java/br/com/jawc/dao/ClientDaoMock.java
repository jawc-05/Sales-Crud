package br.com.jawc.dao;

import java.util.Collection;

import br.com.jawc.domain.Client;
import br.com.jawc.domain.Client;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

public class ClientDaoMock implements IClientDao {

	@Override
	public Boolean cadastrar(Client entity) throws TipoChaveNaoEncontradaException {
		return true;
	}

	@Override
	public void excluir(String valor) {
		
	}

	@Override
	public void alterar(Client entity) throws TipoChaveNaoEncontradaException {
		
	}

	@Override
	public Client consultar(String valor) {
		Client client = new Client();
		client.setCpf(valor);
		return client;
	}

	@Override
	public Collection<Client> buscarTodos() {
		return null;
	}


}
