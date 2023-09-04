package com.cart.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	@JsonManagedReference
	@OneToMany(mappedBy = "cart" ,cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<CartItem> iteam=new HashSet<>();
	
	   @OneToOne
	 private  User user;

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
	   

	}
