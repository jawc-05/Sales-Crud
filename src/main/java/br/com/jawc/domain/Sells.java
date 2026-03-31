/**
 * @author jawc
 */
package br.com.jawc.domain;

import br.com.jawc.annotation.TipoChave;
import br.com.jawc.dao.Persistence;

import java.io.ObjectInputFilter;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class Sells implements Persistence {

    public enum STATUS{
        INICIADA, CONCLUIDA, PAUSADA;
    }
    @TipoChave("getCodigo")
    private String codigo;

    private Client client;

    private Set<ProductQuantity> products;

    private BigDecimal valorTotal;

    private Instant dataVenda;

    private ObjectInputFilter.Status status;

    public Sells() {
        products = new HashSet<>();
    }
}
