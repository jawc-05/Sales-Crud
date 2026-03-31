/**
 * @author jawc
 */
package br.com.jawc.dao;

import br.com.jawc.dao.generic.GenericDao;
import br.com.jawc.dao.generic.IGenericDao;
import br.com.jawc.domain.Client;

public class ClientDao extends GenericDao<Client, String> implements IClientDao {

    public ClientDao() {
        super();
    }

    @Override
    public Class<Client> getTipoClasse() {
        return Client.class;
    }

    @Override
    public void atualizarDados(Client entity, Client entityCadastrado) {
        entityCadastrado.setCidade(entity.getCidade());
        entityCadastrado.setCpf(entity.getCpf());
        entityCadastrado.setEnd(entity.getEnd());
        entityCadastrado.setEstado(entity.getEstado());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setNumero(entity.getNumero());
        entityCadastrado.setTel(entity.getTel());

    }

}
