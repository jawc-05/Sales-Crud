/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Client;

import java.util.List;

public interface IClientDao {

    public Integer sign(Client client) throws Exception;

    public Client search(String cpf) throws Exception;

    public Integer delete(Client clientBD) throws Exception;

    public List<Client> searchAll() throws Exception;

    public Integer update(Client client)throws Exception;
}
