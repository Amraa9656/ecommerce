package mn.ecommerce.ecommerce.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.dto.ResProduct;
import mn.ecommerce.ecommerce.dto.ResProductPrice;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ReqProductDto;
import mn.ecommerce.ecommerce.service.HandleService;
import mn.ecommerce.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("product")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final HandleService handleService;

    @GetMapping("{id}")
    public ResponseEntity<ResProductPrice> getByProductId(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (Exception e){
            log.error("[+] Error " + e.getMessage());
            throw e;
        }
    }

    @GetMapping("list")
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            return ResponseEntity.ok().body(productService.getProducts());
        }catch (Exception e)
        {
            log.error("[+] Error " + e.getMessage());
            throw e;
        }
    }

    @PostMapping("create")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ReqProductDto reqProductDto) throws Exception {
        try {
            return ResponseEntity.ok().body(handleService.createOrFail(reqProductDto));
        }catch (Exception e)
        {
            log.error("[+] Error: " + e.getMessage());
            throw e;

        }
    }
    @GetMapping("page")
    public ResponseEntity<Page<Product>> page(@RequestParam(name = "size", defaultValue = "20") Integer size,
                                              @RequestParam("page") Integer page) {
        try {
            return ResponseEntity.ok(productService.getPageProduct(PageRequest.of(page, size)));
        }catch (Exception e)
        {
            log.error("[+] Error: " + e.getMessage());
            throw e;
        }
    }

//    @GetMapping("search")
//    public ResponseEntity<Page<?>> search(
//    @RequestParam(name = "productName", required = false) String productName,
//    @RequestParam(name = "discount", required = false) Integer discount,
//    @RequestParam(name= "size", defaultValue = "3") Integer size,
//    @RequestParam(name = "page" ) Integer page
//    ){
//
//        try {
//            return ResponseEntity.ok(productService.searchProductOrDiscount(productName, discount, PageRequest.of(page, size)));
//        }catch (Exception e)
//        {
//            throw e;
//        }
//    }
@GetMapping("product")
public ResponseEntity<ResProduct> resProduct(@RequestParam(name = "priceId") Long priceId) {
    try {
        return ResponseEntity.ok(productService.getByPriceId(priceId));
    }catch (Exception e)
    {
        log.error("[+] Error: " + e.getMessage());
        throw e;
    }
}

    @GetMapping("searchProductName")
    public ResponseEntity<List<Product>> searchProductName(@RequestParam(name = "productName") String productName) {
        try {
            return ResponseEntity.ok(productService.getByProductId(productName));
        }catch (Exception e)
        {
            log.error("[+] Error: " + e.getMessage());
            throw e;
        }
    }
    @PutMapping("product")
    public ResponseEntity<Product> putProduct(@RequestParam(name = "id") Long id, @RequestParam(name = "productName") String productName){
        try {
            return ResponseEntity.ok(productService.putProduct(id, productName));
        }catch (Exception e){
            throw e;
        }
    }

//    @PutMapping("discountStatusUpdate")
//    public ResponseEntity<Integer> searchProductName(@RequestBody @Valid ProductIdDto req) {
//        System.out.println("[+] Test " + req.getId());
//        try {
//            return ResponseEntity.ok(productService.updateDiscount(req.getId()));
//        }catch (Exception e)
//        {
//            log.error("[+] Error: " + e.getMessage());
//            throw e;
//        }
//    }

//    @GetMapping("test")
//    public ResponseEntity<String>

}
