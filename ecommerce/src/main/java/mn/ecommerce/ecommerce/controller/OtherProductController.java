//package mn.ecommerce.ecommerce.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import mn.ecommerce.ecommerce.HttpClient.HttpClient;
//import mn.ecommerce.ecommerce.model.OtherProduct;
//import mn.ecommerce.ecommerce.service.OtherProductService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//@RestController("otherProduct")
//@RequestMapping("/otherPRoduct")
//public class OtherProductController {
//
//    private final OtherProductService service;
//
//    public OtherProductController(OtherProductService service) {
//        this.service = service;
//    }
//
//    @GetMapping("findAll")
//    ResponseEntity<List<OtherProduct>> findAll(){
//        try {
//            return ResponseEntity.ok(service.findALl());
//        }catch (Exception e){
//            throw e;
//        }
//    }
//
//    @GetMapping("findById")
//    ResponseEntity<OtherProduct> finById(@RequestParam(name = "id")String id){
//        try {
//            return ResponseEntity.ok(service.findById(id));
//        }catch (Exception e){
//            throw e;
//        }
//    }
//    @PostMapping("save")
//    ResponseEntity<OtherProduct> save(@RequestBody OtherProduct product){
//        try {
//            return ResponseEntity.ok(service.save(product));
//        }catch (Exception e){
//            throw e;
//        }
//    }
//
//}
