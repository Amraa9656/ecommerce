package mn.ecommerce.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.dto.ResProduct;
import mn.ecommerce.ecommerce.dto.ResProductDetail;
import mn.ecommerce.ecommerce.dto.ResProductPrice;
import mn.ecommerce.ecommerce.model.Price;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ProductType;
import mn.ecommerce.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

//    public ResProductPrice getProductById(long productId) {
//        return productRepo.getProductAndPrice(productId);
//    }


    public Page<Product> getPageProduct(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

        public <T> Page<T> searchProductOrDiscount(String productName, List<ProductType> productType, Pageable pageable) {
       return (Page<T>) productRepo.search2(productName, productType,pageable, Product.class);

    }

    /**
     * @param productName
     * @return Product
     */
    public List<Product> getByProductId(String productName) {
        return productRepo.findByProductNameIgnoreCase(productName);
    }


//    public ResProduct getByPriceId(Long priceId) {
//        return productRepo.getProductByPriceId(priceId);
//    }

    /**
     * @param productName
     * @return
     */
    public Product getByProductName(String productName) {
        return productRepo.getProductByProductName(productName);
    }

    /**
     * @param id
     * @param productName
     * @return
     */
    public Product putProduct(Long id, String productName) {
        Optional<Product> product = null;
        Integer isUpdate = productRepo.updateProduct(id, productName);
        if (isUpdate > 0) {
            product = productRepo.findById(id);
        }
        return product.orElseThrow(ArithmeticException::new);
    }

    /**
     * @param id
     * @param productName
     * @return
     */
    public Product putProductSet(Long id, String productName) {
        Product product = productRepo.findById(id).orElse(null);
        if (!Objects.nonNull(product)) {
            throw new ArithmeticException("Error");
        }
        product.setProductName(productName);
        return productRepo.save(product);
    }


    public Product getByProductId(Long id){
        return productRepo.findById(id).orElse(null);
    }

    public List<ResProductDetail> getProductsDetails() {
        return productRepo.getProductDetail();
    }

    public String deleteProduct(Long id) {
        try {
            productRepo.deleteById(id);
        }catch (Exception e){
            throw e;
        }
        return "Success";
    }
}
