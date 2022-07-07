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
    public Product createOrFail(ReqProductDto req)throws Exception {
        Product product = null;
     if (productService.getByProductName(req.getProductName())==null && req.getDiscount()<=100 )
     {
         Price price = Price.builder()
                 .discount(req.getDiscount())
                 .price(req.getPrice())
                 .isActive(req.getIsActive())
                 .build();

         product = Product.builder()
                 .productName(req.getProductName())
                 .priceId(priceService.createPrice(price).getId())
                 .stock(req.getStock())
                 .build();
     }else {
         throw new Exception("True");
     }

      return productService.createProduct(product);
    }
}
