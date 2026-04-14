/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Product;

import java.util.List;

public interface IProductDao {

    public Integer sign(Product product) throws Exception;

    public Product search(String name) throws Exception;

    public Integer delete(Product productDB) throws Exception;

    public List<Product> searchAll() throws Exception;

    public Integer update(Product product)throws Exception;

}
