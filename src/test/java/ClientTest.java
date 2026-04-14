import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jawc
 */

public class ClientTest {

    @Test
    public void signClient() throws Exception {
        IClientDao dao = new ClientDao();
        Client client = new Client();
        client.setCpf("01");
        client.setName("João Alfredo");

        Integer count = dao.sign(client);
        assertTrue(count == 1);

        Client clientBD = dao.search(client.getCpf());
        assertNotNull(clientBD);
        assertNotNull(clientBD.getId());
        assertEquals(client.getCpf(), clientBD.getCpf());
        assertEquals(client.getName(), clientBD.getName());



        Integer countDel = dao.delete(clientBD);
        assertNotNull(countDel);
    }

    @Test
    public void searchAllClient() throws Exception {
        IClientDao dao = new ClientDao();

        Client client = new Client();
        client.setCpf("01");
        client.setName("João Alfredo");
        Integer count = dao.sign(client);
        assertTrue(count == 1);

        Client client2 = new Client();
        client2.setCpf("02");
        client2.setName("Cecília WIlliges");
        Integer count2 = dao.sign(client2);
        assertTrue(count2 == 1);

        List<Client> list = dao.searchAll();
        assertNotNull(list);
        assertEquals(2, list.size());

        int CountDel = 0;
        for (Client cli : list) {
            dao.delete(cli);
            CountDel++;
        }
        assertEquals(list.size(), CountDel);

        list = dao.searchAll();
        assertEquals(list.size(), 0);
    }
}
