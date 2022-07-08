package mn.ecommerce.ecommerce.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.model.Price;
import mn.ecommerce.ecommerce.service.HandleService;
import mn.ecommerce.ecommerce.service.PriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("Price")
@AllArgsConstructor
@Slf4j
public class PriceController {

    private final PriceService priceService;
    private final HandleService handleService;

    @PostMapping("save")
    public Price createPrice(@RequestBody Price price) {
        return priceService.createPrice(price);
    }

}
