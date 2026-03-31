/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.domain.Client;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

public interface IClientService {

    Boolean cadastrar(Client client) throws TipoChaveNaoEncontradaException;

    Client searchByCpf(String cpf);

    void delete(String cpf);

    void update(Client client) throws TipoChaveNaoEncontradaException;
}
