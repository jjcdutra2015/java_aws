package com.github.jjcdutra.aws_project01.model;

public class ProductEvent {

    private long productId;
    private String code;
    private String username;

    public ProductEvent() {
    }

    public ProductEvent(long productId, String code, String username) {
        this.productId = productId;
        this.code = code;
        this.username = username;
    }

    public long getProductId() {
        return productId;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }
}
