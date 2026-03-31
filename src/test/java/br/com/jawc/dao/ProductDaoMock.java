/**
 * 
 */
package br.com.jawc.dao;

import java.util.Collection;

import br.com.jawc.domain.Product;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

/**
 * @author rodrigo.pires
 *
 */
public class ProductDaoMock implements IProductDao {

	@Override
	public Boolean cadastrar(Product entity) throws TipoChaveNaoEncontradaException {
		return true;
	}

	@Override
	public void excluir(String valor) {
		
	}

	@Override
	public void alterar(Product entity) throws TipoChaveNaoEncontradaException {
	}

	@Override
	public Product consultar(String valor) {
		Product product = new Product();
		product.setCodigo(valor);
		return product;
	}

	@Override
	public Collection<Product> buscarTodos() {
		return null;
	}

}
