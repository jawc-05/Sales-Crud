/**
 * @author jawc
 */
package br.com.jawc.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
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
        private Instant saleDate;

        @Enumerated(EnumType.STRING)
        @Column(name = "STATUS_SALE", nullable = false)
        private Status status;

        private void validateStatus() {
            if (this.status == Status.COMPLETED || this.status == Status.CANCELLED) {
                throw new UnsupportedOperationException("Cannot modify a closed or cancelled sale.");
            }
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

    public Instant getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Instant saleDate) {
        this.saleDate = saleDate;
    }
}

