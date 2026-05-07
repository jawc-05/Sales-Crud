/**
 * @author jawc
 */
package br.com.jawc.services.interfaces;

import br.com.jawc.domain.Sale;
import br.com.jawc.services.generic.IGenericService;

public interface ISaleService extends IGenericService<Sale> {

    public void finishSale(Sale sale) throws Exception;
    public void cancelSale(Sale sale) throws Exception;
}
