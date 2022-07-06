package mn.ecommerce.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface Price{
    Integer getId();
    float getPrice();
    @JsonInclude(JsonInclude.Include.ALWAYS)
    Integer getDiscount();
}
