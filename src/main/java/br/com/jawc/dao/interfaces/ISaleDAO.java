/**
 * @author jawc
 */
package br.com.jawc.dao.interfaces;

import br.com.jawc.dao.generic.IGenericDAO;
import br.com.jawc.domain.Sale;

public interface ISaleDAO extends IGenericDAO<Sale> {

    public void finishSale(Sale sale) throws Exception;
    public void cancelSale(Sale sale) throws Exception;
}
