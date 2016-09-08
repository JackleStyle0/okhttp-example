package com.rungreangchai.spaky.onebasic.network.model;

public class Product {
    private String productNumber;
    private String productName;
    private String productPrice;
    private String urlImage;

    public Product(String productNumber, String productName, String productPrice, String urlImage) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.productPrice = productPrice;
        this.urlImage = urlImage;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
