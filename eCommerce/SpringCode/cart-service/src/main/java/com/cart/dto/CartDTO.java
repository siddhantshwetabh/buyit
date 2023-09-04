package com.cart.dto;

import com.cart.entities.CartItem;
import com.cart.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class CartDTO {


    private int cartId;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Set<CartItem> getIteam() {
        return iteam;
    }

    public void setIteam(Set<CartItem> iteam) {
        this.iteam = iteam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Set<CartItem> iteam=new HashSet<>();
    @JsonIgnore
    private User user;

}
