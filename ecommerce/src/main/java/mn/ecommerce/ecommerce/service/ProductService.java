package mn.ecommerce.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.dto.ResProduct;
import mn.ecommerce.ecommerce.dto.ResProductPrice;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public ResProductPrice getProductById(long productId) {
        return productRepo.getProductAndPrice(productId);
    }


    public Page<Product> getPageProduct(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    //    public <T> Page<T> searchProductOrDiscount(String productName, Integer discount, Pageable pageable) {
//       return (Page<T>) productRepo.search2(productName, discount,pageable, Price.class);
//
//    }
    public List<Product> getByProductId(String productName) {
        return productRepo.findByProductNameIgnoreCase(productName);
    }

    public ResProduct getByPriceId(Long priceId) {
        return productRepo.getProductByPriceId(priceId);
    }

    public Product getByProductName(String productName) {
        return productRepo.getProductByProductName(productName);
    }

    public Product putProduct(Long id, String productName){
        Optional<Product> product = null;
        Integer isUpdate = productRepo.updateProduct(id, productName);
        if (isUpdate>0){
            product = productRepo.findById(id);
        }
        return product.orElseThrow(ArithmeticException::new);
    }

}
