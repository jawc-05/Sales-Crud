/**
 * @author jawc
 */
package br.com.jawc.services.generic;

import br.com.jawc.dao.Persistence;
import br.com.jawc.dao.generic.IGenericDao;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericService <T extends Persistence, E extends Serializable> implements IGenericService<T,E>{

    protected IGenericDao<T,E> dao;

    public GenericService(IGenericDao<T,E> dao) {
        this.dao = dao;
    }



    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        return this.dao.cadastrar(entity);
    }

    @Override
    public void excluir(E valor) {
        this.dao.excluir(valor);
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        this.dao.alterar(entity);
    }

    @Override
    public T consultar(E valor) {
        return this.dao.consultar(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        return this.dao.buscarTodos();
    }
}
