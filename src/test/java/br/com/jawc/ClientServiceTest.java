/**
 * @author jawc
 */
package br.com.jawc;

import br.com.jawc.dao.ClientDaoMock;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import br.com.jawc.services.ClientService;
import br.com.jawc.services.IClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClientServiceTest {

    private IClientService clientService;
    private Client client;

    public ClientServiceTest(){
        IClientDao mock  = new ClientDaoMock();
        clientService = new ClientService(mock);
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
    }

    @Test
    public void searchClient(){

        Client clientSearched = clientService.searchByCpf(client.getCpf());
        Assert.assertNotNull(clientSearched);
    }

    @Test
    public void saveClient(){
        Boolean ans = clientService.save(client);
        Assert.assertTrue(ans);
    }
}
