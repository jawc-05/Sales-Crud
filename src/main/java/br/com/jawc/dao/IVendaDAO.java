/**
 * 
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.IGenericDAO;
import br.com.jawc.domain.Venda;
import br.com.jawc.exceptions.DAOException;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 *
 */
public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
	
	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
