/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.GenericDAO;
import br.com.jawc.domain.Sale;

public class SaleDAO extends GenericDAO<Sale> implements ISaleDAO{
    @Override
    public void finishSale(Sale sale) throws Exception {
        sale.setStatus(Sale.Status.COMPLETED);
        super.update(sale);
    }

    @Override
    public void cancelSale(Sale sale) throws Exception {
        sale.setStatus(Sale.Status.CANCELLED);
        super.update(sale);
    }
}
