/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.GenericDAO;
import br.com.jawc.dao.interfaces.IProductDAO;
import br.com.jawc.domain.Product;
import jakarta.ejb.Stateless;

@Stateless
public class ProductDAO extends GenericDAO<Product> implements IProductDAO {

    public ProductDAO(Class<Product> persistentClass) {
        super(persistentClass);
    }
}
