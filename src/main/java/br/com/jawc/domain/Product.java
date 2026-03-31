/**
 * @author jawc
 */
package br.com.jawc.domain;

import br.com.jawc.annotation.TipoChave;
import br.com.jawc.dao.Persistence;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Persistence {

    @TipoChave("getCodigo")
    private String codigo;
    private String nome;
    private String descricao;

    private BigDecimal valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
