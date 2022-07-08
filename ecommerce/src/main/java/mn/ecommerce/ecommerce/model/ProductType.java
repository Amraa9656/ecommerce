package mn.ecommerce.ecommerce.model;

public enum ProductType {
    FOOD(1000D, 100000D),
    Book(100D, 200000D),
    CLOTHES(5000D, 3000000D);


    private Double minPrice;
    private Double maxPrice;
    ProductType(Double minPrice, Double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }
}
