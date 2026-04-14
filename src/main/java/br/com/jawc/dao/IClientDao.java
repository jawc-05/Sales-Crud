/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Client;

import java.util.List;

public interface IClientDao {

    public Integer sign(Client client) throws Exception;

    Client search(String cpf) throws Exception;

    Integer delete(Client clientBD) throws Exception;

    List<Client> searchAll() throws Exception;
}
