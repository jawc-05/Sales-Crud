/**
 * @author jawc
 */
package br.com.jawc.domain;

import java.math.BigDecimal;
import java.util.Properties;

public class ProductQuantity {
    private Product product;
    private Integer quantity;
    private BigDecimal valorTotal;

    public ProductQuantity() {
        this.quantity = 0;
        this.valorTotal = BigDecimal.ZERO;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Properties product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void adicionar(Integer quantity) {
        this.quantity += quantity;
        BigDecimal novoValor = this.product.getValor().multiply(BigDecimal.valueOf(quantity));
        BigDecimal novoTotal = this.valorTotal.add(novoValor);
        this.valorTotal = novoTotal;
    }

    public void remover(Integer quantity) {
        this.quantity -= quantity;
        BigDecimal novoValor = this.product.getValor().multiply(BigDecimal.valueOf(quantity));
        this.valorTotal = this.valorTotal.subtract(novoValor);
    }
}
