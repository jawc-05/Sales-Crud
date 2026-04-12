/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.jdbc.ConnectionFactory;
import br.com.jawc.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        return null;
    }
}
