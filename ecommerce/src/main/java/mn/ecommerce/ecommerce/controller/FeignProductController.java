//package mn.ecommerce.ecommerce.controller;
//
//import mn.ecommerceSdk.Model.Product;
//import mn.ecommerceSdk.ProductFeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController("feign")
//@RequestMapping("feign")
//public class FeignProductController {
//
//    private final ProductFeignClient productFeignClient;
//
//    public FeignProductController(ProductFeignClient productFeignClient) {
//        this.productFeignClient = productFeignClient;
//    }
//
//    @PostMapping("save")
//    public ResponseEntity<Product> save(@RequestBody Product product){
//        return productFeignClient.save(product);
//    }
//
//    @GetMapping("findAll")
//    public ResponseEntity<List<Product>> findAll(){
//        return productFeignClient.findAll();
//    }
//
//    @GetMapping("findById")
//    public ResponseEntity<Product> findById(@RequestParam(name = "id") long id){
//        return productFeignClient.findById(id);
//    }
//
//    @DeleteMapping("remove")
//    public ResponseEntity<String> remove(@RequestParam(name = "id") long id){
//        return productFeignClient.remove(id);
//    }
//
//
//}
