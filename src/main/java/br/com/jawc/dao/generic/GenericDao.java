/**
 * @author jawc
 */
package br.com.jawc.dao.generic;

import br.com.jawc.dao.Persistence;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericDao <T extends Persistence, E extends Serializable> implements IGenericDao<T, E> {

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        return null;
    }

    @Override
    public void excluir(E valor) {

    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {

    }

    @Override
    public T consultar(E valor) {
        return null;
    }

    @Override
    public Collection<T> buscarTodos() {
        return List.of();
    }
}
