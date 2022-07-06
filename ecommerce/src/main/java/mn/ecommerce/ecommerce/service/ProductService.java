package mn.ecommerce.ecommerce.service;

import mn.ecommerce.ecommerce.dto.ProductShortInfo;
import mn.ecommerce.ecommerce.model.Demo;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ReqProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product createProduct(ReqProductDto product);
    List<Product> getProducts();
    Product getProductById(int productId);
    Page<Product> getPageProduct(Pageable pageable);

    <T> Page<T> searchProductOrDiscount(String productName , Integer discount, Pageable pageable);
    List<Product> getByProductId(String productName);


}
