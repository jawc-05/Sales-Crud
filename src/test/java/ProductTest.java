import br.com.jawc.dao.ProductDAO;
import br.com.jawc.domain.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jawc
 */

public class ProductTest {

    @Test
    public void productTest(){
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
        product.setName("Pasta");
        product.setDescription("Penne");
        product.setCode("FOOD-001");
        product.setPrice(new BigDecimal(3.50));
        productDAO.save(product);

        Assert.assertNotNull(product.getId());

        Product productConsulted = productDAO.findById(product.getId());
        Assert.assertEquals(product.getName(),productConsulted.getName());

        Product product2 = new  Product();
        product2.setName("Rice");
        product2.setDescription("Brazilian Rice");
        product2.setCode("FOOD-002");
        product2.setPrice(new BigDecimal(5.45));
        productDAO.save(product2);
        Assert.assertNotNull(product2.getId());

        List list = productDAO.findAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(),2);

        product2.setPrice(new BigDecimal(4.99));
        product2.setDescription("BIG OFFER");
        productDAO.update(product2);
        Product product2Consulted =  productDAO.findById(product2.getId());
        Assert.assertEquals(product2.getName(),product2Consulted.getName());

        productDAO.delete(product2);
        productDAO.delete(product);
        Assert.assertNull(productDAO.findById(product.getId()));
    }
}
