/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.domain.Client;

public interface IClientService {
    void save(Client client);

    Client searchByCpf(String cpf);
}
