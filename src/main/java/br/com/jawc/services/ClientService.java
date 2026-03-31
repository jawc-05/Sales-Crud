/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;
import br.com.jawc.services.generic.GenericService;
import br.com.jawc.services.generic.IGenericService;

public class ClientService extends GenericService<Client, String> implements IClientService {

    //private IClienteDAO clienteDAO;

    public ClientService(IClientDao clientDAO) {
        super(clientDAO);
        //this.clienteDAO = clienteDAO;
    }

//	@Override
//	public Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException {
//		return clienteDAO.cadastrar(cliente);
//	}

    @Override
    public Client searchByCpf(String cpf) {
        return this.dao.consultar(cpf);
    }

    @Override
    public void delete(String cpf) {

    }

    @Override
    public void update(Client client) throws TipoChaveNaoEncontradaException {

    }

//	@Override
//	public void excluir(Long cpf) {
//		clienteDAO.excluir(cpf);
//	}
//
//	@Override
//	public void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException{
//		clienteDAO.alterar(cliente);
//	}
}
