package mn.ecommerce.ecommerce.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Price")
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "discount", nullable = false)
    @ColumnDefault("0")
    private Integer discount;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("0")
    private Integer isActive;

//    @PrePersist
//    public void prePersist(){
//        if (this.discount==null) {
//            this.discount = 0;
//        }
//        if (this.isActive==null)
//            this.isActive = 0;
//    }

}