/**
 * @author jawc
 */
package br.com.jawc;

import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.ClientDaoMock;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import org.junit.Before;
import org.junit.Test;

public class ClientDaoTest {

    private IClientDao clientDao;
    private Client client;

    public ClientDaoTest() {
        clientDao = new ClientDaoMock();
    }

    @Before
    public void init(){
        client = new Client();
        client.setCpf("1234567891011");
        client.setName("João");
        client.setCity("Lublin");
        client.setEnd("Ikar Dorm");
        client.setState("Lubelskie");
        client.setTel(48123213L);
        client.setNum(123);

        clientDao.save(client);
    }

    @Test
    public void searchClient(){

    }
}
