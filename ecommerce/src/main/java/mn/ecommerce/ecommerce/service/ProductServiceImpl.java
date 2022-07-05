package mn.ecommerce.ecommerce.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.dto.ProductShortInfo;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ReqProductDto;
import mn.ecommerce.ecommerce.repository.productRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final productRepo productRepo;

    @Override
    public Product createProduct(ReqProductDto req) {
        Product product1 = new Product();
        try {
            product1.setProductName(req.getProductName());
            product1.setPrice(req.getPrice());
            product1.setStock(req.getStock());
            product1  = productRepo.save(product1);

        }catch (Exception e)
        {
            log.error("[+] Error : " + e.getMessage());
            throw e;
        }

        return product1;
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(int productId) {
       Optional<Product> product = productRepo.findById(productId);
        return product.orElseThrow(ArithmeticException::new);
    }


    @Override
    public Page<Product> getPageProduct(Pageable pageable) {
       return  productRepo.findAll(pageable);
    }

    @Override
    public Page<ProductShortInfo> searchProductOrDiscount(String productName, Integer discount, Pageable pageable) {

        try {
            return productRepo.search(productName.toLowerCase(), discount, pageable);

        }catch (NullPointerException n)
        {
            return productRepo.search(productName, discount, pageable);
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    @Override
    public List<Product> getByProductId(String productName) {
        return productRepo.findByProductNameIgnoreCase(productName);
    }
}
