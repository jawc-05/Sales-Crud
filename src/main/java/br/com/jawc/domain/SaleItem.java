/**
 * @author jawc
 */
package br.com.jawc.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_sale_item")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_item_seq")
    @SequenceGenerator(name="sale_item_seq", sequenceName = "seq_sale_item", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public SaleItem() {
        this.quantity = 0;
        this.totalValue = BigDecimal.ZERO;
    }

    public void add(Integer quantity) {
        this.quantity += quantity;

        BigDecimal itemPrice = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));

        this.totalValue = this.totalValue.add(itemPrice);
    }

    public void remove(Integer quantity) {
        this.quantity -= quantity;

        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));

        this.totalValue = this.totalValue.subtract(newValue);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}

