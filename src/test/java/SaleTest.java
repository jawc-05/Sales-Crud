import br.com.jawc.dao.ClientDAO;
import br.com.jawc.dao.ProductDAO;
import br.com.jawc.dao.SaleDAO;
import br.com.jawc.domain.Client;
import br.com.jawc.domain.Product;
import br.com.jawc.domain.Sale;
import br.com.jawc.domain.SaleItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

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
        this.saleDAO = new SaleDAO(Sale.class);
        this.productDAO = new ProductDAO(Product.class);
        this.clientDAO = new ClientDAO(Client.class);

        this.client = createClient();
        this.product = createProduct("FOOD-001");
    }

    @Test
    public void saleTest(){
         this.sale = new Sale();
        sale.setClient(client);
        sale.addProduct(product, 10);
        sale.setStatus(Sale.Status.STARTED);
        sale.setCode("S-0001");
        sale.setSaleDate(Instant.now());
        saleDAO.save(sale);

        Assert.assertNotNull(sale.getId());

        BigDecimal expectedValue = new BigDecimal("35.00");
        Assert.assertTrue(expectedValue.compareTo(sale.getTotalValue()) == 0);

        sale.addProduct(product, 2);
        saleDAO.update(sale);
        BigDecimal expectedValue2 = new BigDecimal("42.00");
        Assert.assertTrue(expectedValue2.compareTo(sale.getTotalValue()) == 0);

        Sale saleConsulted = saleDAO.findById(sale.getId());
        Assert.assertEquals(saleConsulted.getId(), sale.getId());

        Sale sale2 = new  Sale();
        sale2.setClient(client);
        sale2.setCode("S-0002");
        sale2.setClient(createClient2());
        sale2.addProduct(createProduct2("FOOD-0002"), 5);
        sale2.setStatus(Sale.Status.STARTED);
        sale2.setSaleDate(Instant.now());
        saleDAO.save(sale2);
        Assert.assertNotNull(sale2.getId());

        List list =  saleDAO.findAll();
        Assert.assertEquals(list.size(), 2);

        sale.removeProduct(product, 2);
        saleDAO.update(sale);
        Sale saleConsulted2 = saleDAO.findById(sale.getId());

        Assert.assertEquals(1, sale.getItems().size());
        SaleItem item = sale.getItems().iterator().next();
        Assert.assertEquals(Integer.valueOf(10), item.getQuantity());

    }

    private Client createClient(){
        Client client = new Client();

        client.setName("Jawc");
        client.setCpf("0123456789");
        client.setTel("5555999103635");
        client.setState("Rio Grande do SUl");
        client.setCity("Ijuí");
        client.setAddress("Rua do Comércio");
        client.setNum(123);
        clientDAO.save(client);
        return client;
    }

    private Client createClient2(){
        Client client2 = new Client();

        client2.setName("Cecília");
        client2.setCpf("123456789");
        client2.setTel("9999999999999");
        client2.setState("Rio Grande do SUl");
        client2.setCity("Santa Maria");
        client2.setAddress("Camobi");
        client2.setNum(123);
        clientDAO.save(client2);
        return client2;
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

    private Product createProduct2(String code){
        Product product2 = new  Product();

        product2.setName("Rice");
        product2.setDescription("Brazilian Rice");
        product2.setCode(code);
        product2.setPrice(new BigDecimal(5.45));
        productDAO.save(product2);
        return product2;
    }
}
