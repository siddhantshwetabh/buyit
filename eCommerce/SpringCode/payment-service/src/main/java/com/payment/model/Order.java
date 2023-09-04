package com.payment.model;

import javax.persistence.*;
import java.util.Date;


@Entity(name="ecom_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private String orderStatus;
	private String paymentStatus;	
	private Date orderCreated;
	private Double orderAmount;
	private String billingAddress;
	private Date orderDelivered;

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public Date getOrderCreated() {
		return orderCreated;
	}


	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}


	public Double getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(Double orderAmout) {
		this.orderAmount = orderAmout;
	}


	public String getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}


	public Date getOrderDelivered() {
		return orderDelivered;
	}


	public void setOrderDelivered(Date orderDelivered) {
		this.orderDelivered = orderDelivered;
	}


}
