package mn.ecommerce.ecommerce.repository;

import mn.ecommerce.ecommerce.dto.ProductShortInfo;
import mn.ecommerce.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepo extends JpaRepository<Product, Integer> {

//    @Query("SELECT p FROM Product as p where discount > 1")
//    Optional<Product> findAllProduct();

//        @Query("SELECT p FROM Product as p WHERE p.productName=?productName")
    List<Product> findByProductNameIgnoreCase(String productName);

    List<Product> findByDiscount(Integer discount);

    List<Product> findByProductNameAndDiscount(String productName, Integer discount);

    //productName is null

    @Query("select new mn.ecommerce.ecommerce.dto.ProductShortInfo(p.id, p.productName) from Product as p where (:productName is null or LOWER(p.productName) = :productName) and (:discount is null or p.discount=:discount)")
    Page<ProductShortInfo> search(String productName, Integer discount, Pageable pageable);


//    , Integer discount, Pageable pageable

}
