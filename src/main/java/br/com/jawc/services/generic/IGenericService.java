/**
 * @author jawc
 */
package br.com.jawc.services.generic;

import br.com.jawc.dao.Persistence;
import br.com.jawc.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService<T extends Persistence, E extends Serializable> {

    /**
     * Método para excluir um registro do banco de dados
     *
     * valor chave única do dado a ser excluído
     */
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    /**
     * Método para excluir um registro do banco de dados
     *
     * @param valor chave única do dado a ser excluído
     */
    public void excluir(E valor);

    /**
     *Método para alterar um registro no bando de dados.
     *
     * @param entity a ser atualizado
     */
    public void alterar(T entity) throws TipoChaveNaoEncontradaException;

    /**
     * Método para consultar um registro no banco de dados
     *
     * @param valor chave única do dado a ser consultado
     * @return
     */
    public T consultar(E valor);

    /**
     * Método que irá retornar todos os registros do banco de dados de uma determinado dado ou tabela
     *
     * @return Registros encontrados
     */
    public Collection<T> buscarTodos();

}
