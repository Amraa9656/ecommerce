package mn.ecommerce.ecommerce.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;


    @NotBlank
    @Column(name = "product_name")
    private String productName;

    @Column(name = "price_id", nullable = false)
    private long priceId;

//    @OneToOne
//    @JoinColumn(name = "price_id")
//    @Column(nullable = false)
//    private Price price;

    @Column(name="stock", nullable = false)
    private Integer stock;


//    @Column(name = "create_date", nullable = false)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate createDate;

}
