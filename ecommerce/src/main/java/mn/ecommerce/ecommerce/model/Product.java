package mn.ecommerce.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Price> price;

    @Column(name = "stock", nullable = false)
    private Integer stock;


    @Enumerated(EnumType.STRING)
    private ProductType type;


    @NotBlank
    @Column(name = "product_name")
    private String productName;

}
