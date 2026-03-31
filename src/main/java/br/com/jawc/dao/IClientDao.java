/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.GenericDao;
import br.com.jawc.dao.generic.IGenericDao;
import br.com.jawc.domain.Client;

public interface IClientDao extends IGenericDao<Client, String> {

    Boolean save(Client client);
    Client searchByCpf(String cpf);
    void delete(String cpf);

    void update(Client client);
}
