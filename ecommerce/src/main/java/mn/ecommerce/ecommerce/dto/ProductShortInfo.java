package mn.ecommerce.ecommerce.dto;

public class ProductShortInfo {
    private Long id;
    private String productName;

    public ProductShortInfo(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }


//    protected ProductShortInfo() {
//    }

    public ProductShortInfo() {
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
}
