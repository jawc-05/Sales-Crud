/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.dao.interfaces.IClientDAO;
import br.com.jawc.domain.Client;
import br.com.jawc.services.generic.GenericService;
import br.com.jawc.services.interfaces.IClientService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ClientService extends GenericService<Client, IClientDAO> implements IClientService {

    @Inject
    public ClientService(IClientDAO dao) {
        super(dao);
    }
}
