/**
 * @author jawc
 */
package br.com.jawc.services.generic;

import br.com.jawc.dao.Persistence;

import java.io.Serializable;

public class GenericService <T extends Persistence, E extends Serializable> implements IGenericService<T,E>{
}
