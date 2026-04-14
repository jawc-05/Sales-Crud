import br.com.jawc.dao.IProductDao;
import br.com.jawc.dao.ProductDao;
import br.com.jawc.domain.Product;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
    }

}
