/**
 * 
 */
package br.com.jawc;

import br.com.jawc.dao.ClientDao;
import br.com.jawc.dao.SellsDao;
import br.com.jawc.domain.Client;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author jawc
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ Client.class, ClientDaoTest.class,
	ProductServiceTest.class, ProductDaoTest.class,
	SellsDao.class})
public class AllTests {

}
