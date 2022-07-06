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
    private Integer id;


    @NotBlank
    @Column(name = "product_name")
    private String productName;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name="stock", nullable = false)
    private Integer stock;

    @Column(name = "discount", nullable = false)
    @ColumnDefault("0")
    private Integer discount;

    @PrePersist
    public void prePersist(){
        System.out.println("[+] Hello world " + discount);
        if (this.discount==null)
            discount = 0;
    }


//    @Column(name = "create_date", nullable = false)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    private LocalDate createDate;

}
