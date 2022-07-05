package mn.ecommerce.ecommerce.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.dto.ProductShortInfo;
import mn.ecommerce.ecommerce.model.Product;
import mn.ecommerce.ecommerce.model.ReqProductDto;
import mn.ecommerce.ecommerce.service.ProductServiceImpl;
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

    private final ProductServiceImpl productService;

    @GetMapping("{id}")
    public ResponseEntity<Product> getByProductId(@PathVariable("id") int id) {
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
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ReqProductDto product) {
        try {
            return ResponseEntity.ok().body(productService.createProduct(product));
        }catch (Exception e)
        {
            log.error("[+] Error: " + e.getMessage());
            throw e;

        }
    }
    @GetMapping("page")
    public ResponseEntity<Page<Product>> page(@RequestParam(name = "size", defaultValue = "20") Integer size,@RequestParam("page") Integer page) {
        try {
            return ResponseEntity.ok(productService.getPageProduct(PageRequest.of(page, size)));
        }catch (Exception e)
        {
            log.error("[+] Error: " + e.getMessage());
            throw e;
        }
    }

    @GetMapping("search")
    public ResponseEntity<Page<ProductShortInfo>> search(
    @RequestParam(name = "productName", required = false) String productName,
    @RequestParam(name = "discount", required = false) Integer discount,
    @RequestParam(name= "size", defaultValue = "3") Integer size,
    @RequestParam(name = "page" ) Integer page
    ){

        try {
            return ResponseEntity.ok(productService.searchProductOrDiscount(productName, discount, PageRequest.of(page, size)));
        }catch (Exception e)
        {
            throw e;
        }
    }

//    @GetMapping("test")
//    public ResponseEntity<String>

}
