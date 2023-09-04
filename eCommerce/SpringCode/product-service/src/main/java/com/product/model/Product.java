package com.product.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Product")
public class Product{

    /*private static final long serialVersionUID = 1334242344542246457L;*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*@Column(name = "PRODUCT_ID")*/
    private Long productId;

    //@Column(name = "NAME")
    private String productName;

    private String productType;

    //@Column(name = "EMAIL_ID", unique = true)
    private String productDescription;

    //@Column(name = "ROLE")
    private Double productActualPrice;

    private Double productDiscountedPrice;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "product_images",
            joinColumns = {
                    @JoinColumn(name="product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            }

    )
    private Set<Image> productImage;

    @ManyToOne
    @JoinColumn(name="categoryId")
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
