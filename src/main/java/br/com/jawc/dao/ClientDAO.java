/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.GenericDAO;
import br.com.jawc.dao.interfaces.IClientDAO;
import br.com.jawc.domain.Client;
import jakarta.ejb.Stateless;

@Stateless
public class ClientDAO extends GenericDAO<Client> implements IClientDAO {

    public ClientDAO() {
        super(Client.class);
    }

    public ClientDAO(Class<Client> persistentClass) {
        super(persistentClass);
    }
}
