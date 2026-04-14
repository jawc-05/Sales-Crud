/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Product;

import java.util.List;

public class ProductDao implements IProduct{
    @Override
    public Integer sign(Product product) throws Exception {
        return 0;
    }

    @Override
    public Product search(String name) throws Exception {
        return null;
    }

    @Override
    public Integer delete(Product productDB) throws Exception {
        return 0;
    }

    @Override
    public List<Product> searchAll() throws Exception {
        return List.of();
    }

    @Override
    public Integer update(Product product) throws Exception {
        return 0;
    }

    private String getSqlInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_product (name,description) VALUES (?,?)");
        return sb.toString();
    }

    private String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_product WHERE name = ?");
        return sb.toString();
    }

    private String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_clients where name = ?");
        return sb.toString();
    }
}
