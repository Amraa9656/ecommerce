package mn.ecommerce.ecommerce.dto;

public class ProductShortInfo {
    private Integer id;
    private String productName;

    public ProductShortInfo(Integer id, String productName) {
        this.id = id;
        this.productName = productName;
    }


//    protected ProductShortInfo() {
//    }

    public ProductShortInfo() {
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
}
