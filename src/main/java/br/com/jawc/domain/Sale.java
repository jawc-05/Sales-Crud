/**
 * @author jawc
 */
package br.com.jawc.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "tb_sale")
public class Sale {

        public enum Status {
            STARTED, COMPLETED, CANCELLED;

            public static Status getByName(String value) {
                for (Status status : Status.values()) {
                    if (status.name().equalsIgnoreCase(value)) {
                        return status;
                    }
                }
                return null;
            }
        }

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq")
        @SequenceGenerator(name="sale_seq", sequenceName = "seq_sale", initialValue = 1, allocationSize = 1)
        private Long id;

        @Column(name = "code", nullable = false, unique = true)
        private String code;

        @ManyToOne
        @JoinColumn(name = "id_client_fk",
                foreignKey = @ForeignKey(name = "fk_sale_client"),
                referencedColumnName = "id", nullable = false
        )
        private Client client;

        @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
        private Set<SaleItem> items;

        @Column(name = "total_value", nullable = false)
        private BigDecimal totalValue;

        @Column(name = "sale_date", nullable = false)
        private LocalDate saleDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "STATUS_SALE", nullable = false)
        private Status status;

    public Sale() {
        this.items = new HashSet<>();
        this.totalValue = BigDecimal.ZERO;
        this.status = Status.STARTED;
    }

    private void validateStatus() {
            if (this.status == Status.COMPLETED || this.status == Status.CANCELLED) {
                throw new UnsupportedOperationException("Cannot modify a closed or cancelled sale.");
            }
        }

    public void recalculateTotalValue() {
        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem item : this.items) {
            total = total.add(item.getTotalValue());
        }
        this.totalValue = total;
    }

    public void addProduct(Product product, Integer quantity) {
        if (this.items == null) {
            this.items = new HashSet<>();
        }

        validateStatus();


        Optional<SaleItem> op = items.stream()
                .filter(item -> item.getProduct().getCode().equals(product.getCode()))
                .findAny();

        if (op.isPresent()) {
            op.get().add(quantity);
        } else {

            SaleItem newItem = new SaleItem();
            newItem.setSale(this);
            newItem.setProduct(product);
            newItem.add(quantity);
            items.add(newItem);
        }

        recalculateTotalValue();
    }

    //IMPORTANT METHOD
    public void removeProduct(Product product, Integer quantity) {
        validateStatus();

        Optional<SaleItem> op = items.stream()
                .filter(item -> item.getProduct().getCode().equals(product.getCode()))
                .findAny();

        if (op.isPresent()) {
            SaleItem existingItem = op.get();

            // Logic to handle partial removal or full item deletion from the sale
            if(existingItem.getQuantity() > quantity) {
                existingItem.remove(quantity);
            }else{
                // Otherwise, if is equals or bigger we just remove all the items
                items.remove(existingItem);
            }
        }
        recalculateTotalValue();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<SaleItem> getItems() {
        return items;
    }

    public void setItems(Set<SaleItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}

