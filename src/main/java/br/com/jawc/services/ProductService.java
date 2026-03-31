/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.generic.IGenericDao;
import br.com.jawc.domain.Product;
import br.com.jawc.services.generic.GenericService;

public class ProductService extends GenericService<Product, String> implements IProductService{

    public ProductService(IProductDao dao) {
        super(dao);
    }
}
