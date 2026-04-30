/**
 * @author jawc
 */
package br.com.jawc.dao.generic;

import java.util.List;

public interface IGenericDAO<T extends Object>{

    T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}
