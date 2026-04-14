/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDao implements IProductDao {
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

    private String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE tb_product");
        sb.append(" SET name = ?, description = ?");
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    private String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_product");
        return sb.toString();
    }

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) throws SQLException {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
