package mn.ecommerce.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import mn.ecommerce.ecommerce.model.Price;
import mn.ecommerce.ecommerce.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PriceService {
    private final PriceRepository priceRepo;

    public PriceService(PriceRepository priceRepo){
        this.priceRepo = priceRepo;
    }

   public Price getByPriceID(Long id){
       Optional<mn.ecommerce.ecommerce.model.Price> price = priceRepo.findById(id);
       return price.orElseThrow(ArithmeticException::new) ;
   }
   public Price createPrice(Price price){
        return priceRepo.save(price);
   }

}
