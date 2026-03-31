/**
 * @author jawc
 */
package br.com.jawc.dao.generic;

import br.com.jawc.dao.IProductDao;
import br.com.jawc.domain.Product;

public class ProductDao extends GenericDao<Product, String> implements IProductDao {

    public ProductDao(){
        super();
    }

    @Override
    public Class<Product> getTipoClasse() {
        return Product.class;
    }

    @Override
    public void atualizarDados(Product entity, Product entityCadastrado) {
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setDescricao(entity.getDescricao());
        entityCadastrado.setValor(entity.getValor());
        entityCadastrado.setCodigo(entity.getCodigo());
    }
}
