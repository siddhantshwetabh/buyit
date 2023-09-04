package com.order.dto;


import com.order.model.OrderItem;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDto {
	private int orderId;
	private String orderStatus;
	private String paymentStatus;	
	private Date orderCreated;
	private Double orderAmount;
	private String billingAddress;
	private Date orderDelivered;
   private	Set<OrderItem> item=new HashSet<>();

   private UserDto user;


public UserDto getUser() {
	return user;
}
public void setUser(UserDto user) {
	this.user = user;
}
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
public void setOrderAmount(Double orderAmount) {
	this.orderAmount = orderAmount;
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
public Set<OrderItem> getItem() {
	return item;
}
public void setItem(Set<OrderItem> item) {
	this.item = item;
}
   

	
}
