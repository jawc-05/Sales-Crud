/**
 * @author jawc
 */
package br.com.jawc.dao.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDAO<T> implements IGenericDAO<T>{

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }
    protected Class<T> persistentClass;

    @PersistenceContext(unitName = "shopDB")
    protected EntityManager em;

    @Override
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T findById(Long id) {
            return em.find(persistentClass, id);
    }
    @Override
    public T update(T entity) {
        T entityMerged = em.merge(entity);
        return entityMerged;
    }

    @Override
    public void delete(T entity) {
        T entityMerged = em.merge(entity);
        em.remove(entityMerged);
    }



    @Override
    public List findAll() {
        String jpql = "SELECT e FROM " + persistentClass.getName() + " e";
        return em.createQuery(jpql, persistentClass).getResultList();
    }
}
