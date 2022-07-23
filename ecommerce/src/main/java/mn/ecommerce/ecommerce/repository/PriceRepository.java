package mn.ecommerce.ecommerce.repository;

import mn.ecommerce.ecommerce.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price, Long> {
//    void deleteByProductId(Long id);
    @Modifying
    @Query("delete  from Price p where p.product.id =:id and p.isActive = false ")
    void deleteByProductId(Long id);

}
