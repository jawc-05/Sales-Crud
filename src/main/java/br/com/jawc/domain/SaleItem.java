/**
 * @author jawc
 */
package br.com.jawc.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_sale_item")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_item_seq")
    @SequenceGenerator(name="sale_item_seq", sequenceName = "seq_sale_item", initialValue = 1, allocationSize = 1)
    private Long id;
}
