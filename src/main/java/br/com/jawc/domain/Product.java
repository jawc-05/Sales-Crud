/**
 * @author jawc
 */
package br.com.jawc.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_prod")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
    @SequenceGenerator(name="prod_seq", sequenceName = "seq_prod", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "name",  nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "price",  nullable = false)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
