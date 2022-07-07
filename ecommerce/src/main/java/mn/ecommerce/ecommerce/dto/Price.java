package mn.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Price{
    Integer getId();
    float getPrice();
    @JsonInclude(JsonInclude.Include.ALWAYS)
    Integer getDiscount();
    Integer getDiscountStatus();

}
