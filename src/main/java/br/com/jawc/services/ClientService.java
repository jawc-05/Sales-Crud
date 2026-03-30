/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;

public class ClientService implements IClientService {

    private IClientDao clientDao;

    public ClientService(IClientDao clientDao) {
         this.clientDao = clientDao;
    }

    @Override
    public Boolean save(Client client) {
        return clientDao.save(client);
    }

    @Override
    public Client searchByCpf(String cpf) {
        return clientDao.searchByCpf(cpf);
    }
}
