/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.jdbc.ConnectionFactory;
import br.com.jawc.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientDao implements IClientDao {

    @Override
    public Integer sign(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = "INSERT INTO tb_clients (cpf,name) values(?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCpf());
            stm.setString(2, client.getName());
            return stm.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            if(stm != null && !stm.isClosed()){
                stm.close();
            }
            if(connection != null && !connection.isClosed()){
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
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = "SELECT * FROM tb_clients WHERE cpf = ?";
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
        }catch(Exception e){
            throw e;
        }finally{
            if(stm != null && !stm.isClosed()){
                stm.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }

    @Override
    public Integer delete(Client client) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        try{
            connection = ConnectionFactory.getConnection(connection);
            String sql = "DELETE FROM tb_clients where cpf = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, client.getCpf());
            return stm.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            if(stm != null && !stm.isClosed()){
                stm.close();
            }
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
    }
}
