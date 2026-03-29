/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Client;

public class ClientDaoMock implements IClientDao {
    @Override
    public void save(Client client) {

    }

    @Override
    public Client searchByCpf(String cpf) {
        Client client = new Client();
        client.setCpf(cpf);
        return client;
    }
}
