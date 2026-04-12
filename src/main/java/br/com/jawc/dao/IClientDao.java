/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Client;

public interface IClientDao {

    public Integer sign(Client client) throws Exception;

    Client search(String cpf) throws Exception;
}
