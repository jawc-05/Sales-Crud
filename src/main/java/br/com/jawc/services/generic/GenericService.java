/**
 * @author jawc
 */
package br.com.jawc.services.generic;

import br.com.jawc.dao.generic.GenericDAO;
import br.com.jawc.dao.generic.IGenericDAO;

import java.util.List;

public abstract class GenericService <T, DAO extends IGenericDAO<T>> implements IGenericService<T> {
    protected DAO dao;

    // CONTRUCTOR TO THE SPECIFIC SERVICE GIVES THE RIGHT DAO
    public GenericService(DAO dao) {
        this.dao = dao;
    }

    @Override
    public T save(T entity) {
        return this.dao.save(entity);
    }

    @Override
    public T update(T entity) {
        return this.dao.update(entity);
    }

    @Override
    public void delete(T entity) {
        this.dao.delete(entity);
    }

    @Override
    public T findById(Long id) {
        return this.dao.findById(id);
    }

    @Override
    public List<T> findAll() {
        return this.dao.findAll();
    }
}
