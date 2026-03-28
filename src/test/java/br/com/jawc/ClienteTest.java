/**
 * @author jawc
 */
package br.com.jawc;

import br.com.jawc.domain.Client;
import br.com.jawc.services.IClienteService;
import org.junit.Test;

public class ClienteTest {

    private IClienteService clienteService;

    @Test
    public void searchCliente(){
        Client cliente = new Client();
        cliente.setCpf("1234567891011");
        cliente.setName("João");
        cliente.setCity("Lublin");
        cliente.setEnd("Ikar Dorm");
        cliente.setState("Lubelskie");
        cliente.setTel(48123213L);
    }
}
