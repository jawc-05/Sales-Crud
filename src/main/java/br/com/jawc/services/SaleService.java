/**
 * @author jawc
 */
package br.com.jawc.services;

import br.com.jawc.dao.interfaces.ISaleDAO;
import br.com.jawc.domain.Sale;
import br.com.jawc.services.generic.GenericService;
import br.com.jawc.services.generic.IGenericService;
import br.com.jawc.services.interfaces.ISaleService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class SaleService extends GenericService<Sale, ISaleDAO> implements ISaleService {

    @Inject
    public SaleService(ISaleDAO dao) {
        super(dao);
    }

    @Override
    public void finishSale(Sale sale) throws Exception {
        dao.finishSale(sale);
    }

    @Override
    public void cancelSale(Sale sale) throws Exception {
        dao.cancelSale(sale);
    }
}
