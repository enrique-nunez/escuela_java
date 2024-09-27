package pe.com.enrique.nunez.escuelajava.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(schema = "db_tech_emporium",name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Product {
    private static final long serialVersionUID = -348108291780327950L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize = 1)
    @Column(name = "product_id")
    private Integer productId;
    @Column
    private String productName;
    @Column
    private BigDecimal productPrice;
    @Column
    private Boolean status;
}
