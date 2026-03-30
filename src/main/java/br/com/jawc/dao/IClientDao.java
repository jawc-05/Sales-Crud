/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Client;

public interface IClientDao {

    Boolean save(Client client);
    Client searchByCpf(String cpf);
    void delete(String cpf);

    void update(Client client);
}
