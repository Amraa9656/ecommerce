package mn.ecommerce.ecommerce.dto;

import lombok.Getter;
import mn.ecommerce.ecommerce.model.Price;

public class ResProductPrice {

    private Long id;
    private String productName;
    private Long priceId;
    private Price price;
    private Integer discount;

//    protected ResProductPrice(){}


    public ResProductPrice(Long id, String productName, Long priceId, Price price, Integer discount) {
        this.id = id;
        this.productName = productName;
        this.priceId = priceId;
        this.price = price;
        this.discount = discount;
    }

    public ResProductPrice() {
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Long getPriceId() {
        return priceId;
    }

    public Price getPrice() {
        return price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
