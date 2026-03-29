/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Client;

public interface IClientDao {

    void save(Client client);
    Client searchByCpf(String cpf);
}
