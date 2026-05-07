/**
 * @author jawc
 */
package br.com.jawc.services.generic;

import java.util.List;

public interface IGenericService<T extends Object> {

    T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}

