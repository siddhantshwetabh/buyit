package com.product.dto;

import com.product.model.Category;
import com.product.model.Image;

import java.util.Set;

public class ProductDTO {

    private Long productId;

    private String productName;

    private String productType;

    private String productDescription;

    private Double productActualPrice;

    private Double productDiscountedPrice;

    private Set<Image> productImage;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<Image> getProductImage() {
        return productImage;
    }

    public void setProductImage(Set<Image> productImage) {
        this.productImage = productImage;
    }

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductActualPrice() {
        return productActualPrice;
    }

    public void setProductActualPrice(Double productActualPrice) {
        this.productActualPrice = productActualPrice;
    }

    public Double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    public void setProductDiscountedPrice(Double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

}
