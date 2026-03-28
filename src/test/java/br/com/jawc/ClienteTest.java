/**
 * @author jawc
 */
package br.com.jawc;

import br.com.jawc.domain.Client;
import org.junit.Test;

public class ClienteTest {

    @Test
    public void searchCliente(){
        Client cliente = new Client();
        cliente.setCpf("1234567891011");
    }
}
