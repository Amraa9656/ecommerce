package mn.ecommerce.ecommerce.model;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReqProductDto {
    @NotBlank
    private String productName;

    private ProductType type;

    @NotNull
    @Min(value = 100, message = "hhe")
    @Max(value = 20000000, message = "hho")
    private float price;
    @NotNull
    private Integer stock;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private Integer discount;

    @NotNull
    private Boolean isActive;

    protected ReqProductDto() {
    }
}
