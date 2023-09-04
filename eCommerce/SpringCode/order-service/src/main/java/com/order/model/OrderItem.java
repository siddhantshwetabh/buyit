package com.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	@OneToOne
	private Product product;
	private double totalProductPrize;
	private int quantity;
	@JsonBackReference
	@ManyToOne
    private	Order order;

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getTotalProductPrize() {
		return totalProductPrize;
	}

	public void setTotalProductPrize(double totalProductPrize) {
		this.totalProductPrize = totalProductPrize;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
}
