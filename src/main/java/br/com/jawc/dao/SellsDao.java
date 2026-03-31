/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.GenericDao;
import br.com.jawc.domain.Sells;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

public class SellsDao extends GenericDao<Sells, String> implements ISellsDao {
    @Override
    public void finalizarSell(Sells sell) throws TipoChaveNaoEncontradaException {
        sell.setStatus(Sells.Status.CONCLUIDA);
        super.alterar(sell);
    }

    @Override
    public Class<Sells> getTipoClasse() {
        return Sells.class;
    }

    @Override
    public void atualizarDados(Sells entity, Sells entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setStatus(entity.getStatus());
    }
}
