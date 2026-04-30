import br.com.jawc.dao.ClientDao;
import br.com.jawc.domain.Client;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author jawc
 */

public class ClientTest {

    @Test
    public void createClientTest(){
        ClientDao clientDao = new ClientDao();

        Client client = new Client();
        client.setName("Jawc");
        client.setCpf("0123456789");
        client.setTel(5555999103635l);
        client.setState("Rio Grande do SUl");
        client.setCity("Ijuí");
        client.setAddress("Rua do Comércio");
        client.setNum(123);
        clientDao.save(client);

        Assert.assertNotNull(client.getId());

        Client clientConsulted = clientDao.findById(client.getId());
        Assert.assertEquals(clientConsulted.getId(), client.getId());

        client.setName("João Alfredo");
        clientDao.update(client);
        clientConsulted = clientDao.findById(client.getId());
        Assert.assertEquals(clientConsulted.getName(), client.getName());

        Client client2 = new Client();
        client2.setName("Cecília");
        client2.setCpf("123456789");
        client2.setTel(9999999999999l);
        client2.setState("Rio Grande do SUl");
        client2.setCity("Santa Maria");
        client2.setAddress("Camobi");
        client2.setNum(123);
        clientDao.save(client2);

        List list = clientDao.findAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 2);


        clientDao.delete(client);
        clientDao.delete(client2);
        Assert.assertNull(clientDao.findById(client.getId()));
    }

}
