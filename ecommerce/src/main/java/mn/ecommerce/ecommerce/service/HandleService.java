package mn.ecommerce.ecommerce.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.model.Price;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ReqProductDto;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        if (Objects.isNull(productService.getByProductName(req.getProductName())) && req.getDiscount() <= 100) {
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


    /**
     * delete vildel hiihdee Product iig bn uu vgvig ehleed shalgaad tuhai product dai bvh
     * vnen in idewhgvi tolowd bsan ved ustgan
     * @param id
     * @return SUCCESS OR FAIL
     */
    @SneakyThrows
    public String deleteProduct(Long id) {

        Product product = productService.getByProductId(id);
        if (Objects.nonNull(product)) {
            Price count = product.getPrice().stream()
                    .filter(f -> f.getIsActive())
                    .findAny().orElse(null);
            if (!Objects.isNull(count)) throw new Exception();
            priceService.deletePrice(id);
            productService.deleteProduct(product);
        } else {
            return "Fail";
        }
        return "Success";
    }


}
