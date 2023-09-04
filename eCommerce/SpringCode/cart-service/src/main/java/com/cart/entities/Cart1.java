//package com.cart.entities;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "Cart")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long cartId;
//
//    @OneToOne
//    private  User user;
//    @JsonManagedReference
//    @OneToMany(mappedBy = "cart" ,cascade=CascadeType.ALL,orphanRemoval = true)
//    private Set<CartItem> iteam=new HashSet<>();
//    public Set<CartItem> getIteam() {
//        return iteam;
//    }
//
//    public void setIteam(Set<CartItem> iteam) {
//        this.iteam = iteam;
//    }
//
//
//
//
//
//
//
////    @Column(name = "CUSTOMER_EMAIL")
////    private String customerEmail;
////
////    @Column(name = "TOTAL_AMOUNT")
////    private Double totalAmount;
////
////    @Transient
////    private BillingAdress billingAdresses = new BillingAdress();
////
////    @Transient
////    private ProductDTO productDTO = new ProductDTO();
////
////    public Long getId() {
////        return id;
////    }
////
////    public void setId(Long id) {
////        this.id = id;
////    }
////
////    public String getCustomerEmail() {
////        return customerEmail;
////    }
////
////    public void setCustomerEmail(String customerEmail) {
////        this.customerEmail = customerEmail;
////    }
////
////    public Double getTotalAmount() {
////        return totalAmount;
////    }
////
////    public void setTotalAmount(Double totalAmount) {
////        this.totalAmount = totalAmount;
////    }
//
//}
