import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.ProductDao;
import br.com.jawc.domain.Product;
import org.junit.Assert;
import org.junit.Test;

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

}
