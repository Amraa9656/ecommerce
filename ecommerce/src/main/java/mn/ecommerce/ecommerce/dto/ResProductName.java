package mn.ecommerce.ecommerce.dto;

public class ResProductName implements ResProduct {
    private Long productId;
    private String productName;

    public ResProductName() {
    }

    public ResProductName(Long productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    @Override
    public Long getProductId() {
        return this.productId;
    }

    @Override
    public String getProductName() {
        return this.productName;
    }

//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
}
