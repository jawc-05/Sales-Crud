import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.ProductDao;
import br.com.jawc.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jawc
 */

public class ProductTest {

    @Test
    public void testSignProduct() throws Exception {
        IProductDao dao = new ProductDao();

        Product product = new Product();
        product.setName("TV");
        product.setDescription("50 Polegadas");
        Integer count = dao.sign(product);
        assertTrue(count == 1);

        Product productDB = dao.search(product.getName());
        assertNotNull(productDB);
        assertNotNull(productDB.getId());
        assertEquals(product.getName(), productDB.getName());
        assertEquals(product.getDescription(), productDB.getDescription());

        Integer countDel = dao.delete(productDB);
        assertNotNull(countDel);
    }

    @Test
    public void testSearchAllProduct() throws Exception {
        IProductDao dao = new ProductDao();

        Product product = new Product();
        product.setName("TV LG");
        product.setDescription("50 Polegadas");
        Integer count = dao.sign(product);
        assertTrue(count == 1);

        Product product2 = new Product();
        product2.setName("TV TLC");
        product2.setDescription("65 Polegadas");
        Integer count2 = dao.sign(product2);
        assertTrue(count2 == 1);

        List<Product> list = dao.searchAll();
        assertEquals(2, list.size());

        int countDel = 0;
        for (Product p : list) {
            dao.delete(p);
            countDel++;
        }

        assertEquals(list.size(), countDel);

        list = dao.searchAll();
        assertEquals(list.size(), 0);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        IProductDao dao = new ProductDao();

        Product product = new Product();
        product.setName("TV LG");
        product.setDescription("50 Polegadas");
        Integer count = dao.sign(product);
        assertTrue(count == 1);

        Product productDB = dao.search(product.getName());
        assertNotNull(productDB);
        assertNotNull(productDB.getId());
        assertEquals(product.getName(), productDB.getName());
        assertEquals(product.getDescription(), productDB.getDescription());

        productDB.setName("SOFA");
        productDB.setDescription("BEGE");
        Integer countUp =  dao.update(productDB);
        assertNotNull(countUp);

    }

}
