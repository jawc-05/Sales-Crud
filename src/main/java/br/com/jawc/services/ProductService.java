/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.dao.interfaces.IProductDAO;
import br.com.jawc.domain.Product;
import br.com.jawc.services.generic.GenericService;
import br.com.jawc.services.interfaces.IProductService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ProductService extends GenericService<Product, IProductDAO> implements IProductService {

    @Inject
    public ProductService(IProductDAO dao) {
        super(dao);
    }
}
