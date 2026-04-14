/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.jdbc.ConnectionFactory;
import br.com.jawc.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    @Override
    public Integer sign(Product product) throws Exception {
        PreparedStatement stm = null;
        Connection connection = null;
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public Product search(String name) throws Exception {
        PreparedStatement stm = null;
        Connection connection = null;
        ResultSet rs = null;
        Product product = null;
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            rs = stm.executeQuery();
            while(rs.next()){
                product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
            }
            return product;
        } catch (Exception e) {
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }
    }

    @Override
    public Integer delete(Product productDB) throws Exception {
        PreparedStatement stm = null;
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            stm.setString(1, productDB.getName());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally {
            closeConnection(connection, stm, null);
        }
    }

    @Override
    public List<Product> searchAll() throws Exception {
        PreparedStatement stm = null;
        Connection connection = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        Product product = null;
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                list.add(product);
            }
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, rs);
        }
        return list;
    }

    @Override
    public Integer update(Product product) throws Exception {
        PreparedStatement stm = null;
        Connection connection = null;
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setLong(3, product.getId());
            return stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }finally {
            closeConnection(connection, stm, null);
        }
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
        sb.append("DELETE FROM tb_product where name = ?");
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
