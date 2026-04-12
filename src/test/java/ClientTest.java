import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.IClientDao;
import br.com.jawc.domain.Client;
import org.junit.Test;

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

    }
}
