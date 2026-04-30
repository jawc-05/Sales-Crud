import br.com.jawc.dao.ClientDAO;
import br.com.jawc.dao.ProductDAO;
import br.com.jawc.dao.SaleDAO;
import br.com.jawc.domain.Client;
import br.com.jawc.domain.Product;
import br.com.jawc.domain.Sale;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author jawc
 */

public class SaleTest {

    ProductDAO productDAO;
    Product product;

    ClientDAO clientDAO;
    Client client;

    SaleDAO saleDAO;
    Sale sale;



    @Before
    public void init(){
        this.saleDAO = new SaleDAO();
        this.productDAO = new ProductDAO();
        this.clientDAO = new ClientDAO();

        this.client = createClient();
        this.product = createProduct("FOOD-001");
    }

    private Client createClient(){
        Client client = new Client();

        client.setName("Jawc");
        client.setCpf("0123456789");
        client.setTel(5555999103635l);
        client.setState("Rio Grande do SUl");
        client.setCity("Ijuí");
        client.setAddress("Rua do Comércio");
        client.setNum(123);
        clientDAO.save(client);
        return client;
    }

    private Product createProduct(String code){
        Product product = new Product();
        product.setName("Pasta");
        product.setDescription("Penne");
        product.setCode(code);
        product.setPrice(new BigDecimal(3.50));
        productDAO.save(product);
        return product;
    }
}
