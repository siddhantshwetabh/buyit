package com.cart.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartIteamId;
	
	 @ManyToOne
	@JsonBackReference
    private Cart cart;
    
	private int quantity;
	
	private double totalProductPrize;
	
	@OneToOne
	private Product product;

	public int getCartIteamId() {
		return cartIteamId;
	}

	public void setCartIteamId(int cartIteamId) {
		this.cartIteamId = cartIteamId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalProductPrize() {
		return totalProductPrize;
	}

	public void setTotalProductPrize() {
		this.totalProductPrize = this.quantity*this.product.getProductDiscountedPrice();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


}
