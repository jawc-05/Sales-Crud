/**
 * @author jawc
 */
package br.com.jawc;

import br.com.jawc.domain.Client;
import br.com.jawc.services.IClientService;
import org.junit.Assert;
import org.junit.Test;

public class ClientTest {

    private IClientService clientService;

    @Test
    public void searchClient(){
        Client client = new Client();
        client.setCpf("1234567891011");
        client.setName("João");
        client.setCity("Lublin");
        client.setEnd("Ikar Dorm");
        client.setState("Lubelskie");
        client.setTel(48123213L);
        client.setNum(123);

        clientService.save();

        Client clientSearched = clientService.searchByCpf(client.getCpf());
        Assert.assertNotNull(clientSearched);
    }
}
