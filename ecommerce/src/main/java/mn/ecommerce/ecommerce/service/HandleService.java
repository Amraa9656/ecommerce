package mn.ecommerce.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.model.Price;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ReqProductDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HandleService {
    private final PriceService priceService;
    private final ProductService productService;

    public HandleService(PriceService priceService, ProductService productService) {
        this.priceService = priceService;
        this.productService = productService;
    }

    public Product createOrFail(ReqProductDto req) throws Exception {

        if (req.getType().getMinPrice() >= req.getPrice() || req.getType().getMaxPrice() <= req.getPrice()) {
            throw new ArithmeticException("Error");
        }
        Product product;
        if (productService.getByProductName(req.getProductName()) == null && req.getDiscount() <= 100) {
            product = productService.createProduct(Product.builder()
                    .productName(req.getProductName())
                    .stock(req.getStock())
                    .type(req.getType())
                    .build());
            Price price = Price.builder()
                    .discount(req.getDiscount())
                    .price(req.getPrice())
                    .isActive(req.getIsActive())
                    .product(product)
                    .build();
            priceService.createPrice(price);
        } else {
            throw new Exception("True");
        }

        return product;
    }

    public Product getByProductId(Long id) {
        return productService.getByProductId(id);
    }


    public String deleteProduct(Long id){
        try {
            priceService.deletePrice(id);
            productService.deleteProduct(id);
        }catch (Exception e){
            throw e;
        }
        return "Success";
    }


}
