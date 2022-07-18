package mn.ecommerce.ecommerce.repository;

import mn.ecommerce.ecommerce.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    void deleteByProductId(Long id);
}
