package mn.ecommerce.ecommerce.repository;

import mn.ecommerce.ecommerce.dto.ResProduct;
import mn.ecommerce.ecommerce.dto.ResProductDetail;
import mn.ecommerce.ecommerce.dto.ResProductPrice;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.JoinTable;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM Product as p where discount > 1")
//    Optional<Product> findAllProduct();

    //        @Query("SELECT p FROM Product as p WHERE p.productName=?productName")
    List<Product> findByProductNameIgnoreCase(String productName);

//    @Query("select new mn.ecommerce.ecommerce.dto.ResProductPrice(p.id, p.productName, p.priceId, p1, p1.discount)  " +
//            "from Product p  inner join Price p1 on p.priceId = p1.id where p.priceId = :priceId")
//    ResProductPrice getProductAndPrice(Long priceId);
//
//
//    @Query("select p.id as id, p.productName as name, p1 as price from Product p " +
//            "inner join Price p1 on p.priceId = p1.id and p.priceId = :priceId")
//    ResProduct getProductByPriceId(Long priceId);

    Product getProductByProductName(String productName);

    @Modifying
    @Query("update Product p set p.productName = :productName where p.id=:id")
    Integer updateProduct(Long id, String productName);


    @Query("SELECT p.id as id , p.productName as productName, p.stock as stock, p.type as type," +
            " p1.price as price, p1.discount as discount, p1.id as priceId " +
            "  from Product p, Price p1 where p.id=p1.product.id")
    List<ResProductDetail> getProductDetail();

//    List<Product> findByDiscount(Integer discount);

//    List<Product> findByProductNameAndDiscount(String productName, Integer discount);

//    @Query("select new mn.ecommerce.ecommerce.dto.ProductShortInfo(p.id, p.productName) from Product as p where (:productName is null or LOWER(p.productName) = LOWER(:productName) ) and (:discount is null or p.discount=:discount)")
//    Page<ProductShortInfo> search(String productName, Integer discount, Pageable pageable);

//    @Query("select p.id as id, p.productName as name from Product as p where (:productName is null or LOWER(p.productName) = LOWER(:productName) ) and (:discount is null or p.discount=:discount)")
//    Page<Demo> search1(String productName, Integer discount, Pageable pageable);

    @Query("select p from Product as p where (:productName is null or p.productName = :productName ) and " +
            "((:productType) is null or p.type in (:productType))")
   <T> Page<T> search2(String productName, List<ProductType> productType, Pageable pageable, Class<T> t);





//    @Modifying
//    @Query("update Product p set p.discountStatus = 1 where p.id=:id")
//    Integer updateDiscount(Integer id);

}