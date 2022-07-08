package mn.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import mn.ecommerce.ecommerce.model.ProductType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ResProductDetail {
    Long getId();

    String getProductName();

    Integer getStock();

    ProductType getType();

    Float getPrice();

    Integer getDiscount();

    Long getPriceId();
}
