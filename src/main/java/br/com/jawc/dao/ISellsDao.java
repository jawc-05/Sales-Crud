/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.IGenericDao;
import br.com.jawc.domain.Sells;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

public interface ISellsDao extends IGenericDao<Sells, String> {

    public void finalizarSell(Sells sell) throws TipoChaveNaoEncontradaException;
}
