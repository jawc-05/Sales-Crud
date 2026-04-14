/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.jdbc.ConnectionFactory;
import br.com.jawc.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements IClientDao {

    @Override
    public Integer sign(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCpf());
            stm.setString(2, client.getName());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Client search(String cpf) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Client client = null;
        try {
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            stm.setString(1, cpf);
            rs = stm.executeQuery();
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getLong("id"));
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("name"));
            }
            return client;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer delete(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try {
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCpf());
            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Client> searchAll() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Client> list = new ArrayList<>();
        Client client = null;
        try {
            connection = ConnectionFactory.getConnection(connection);
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                client = new Client();
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String cpf = rs.getString("cpf");
                client.setId(id);
                client.setCpf(cpf);
                client.setName(name);
                list.add(client);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return list;
    }

    private String getSqlInsert(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO tb_clients (cpf,name) VALUES (?,?)");
        return sb.toString();
    }

    private String getSqlSelect(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_clients WHERE cpf = ?");
        return sb.toString();
    }

    private String getSqlDelete(){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM tb_clients where cpf = ?");
        return sb.toString();
    }

    private String getSqlSelectAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM tb_clients");
        return sb.toString();
    }
}
