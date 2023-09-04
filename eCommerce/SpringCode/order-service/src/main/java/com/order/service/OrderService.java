package com.order.service;

import com.order.dto.OrderDto;
import com.order.dto.OrderRequest;

import java.util.List;

public interface OrderService {
 public OrderDto create(OrderRequest request, long userId);
 //Get OrderBy User
 public List<OrderDto> getAllOrder(long userId);
 //Get All Order
 public List<OrderDto>listAllOrder();
 public OrderDto getOrder(int orderId);
 public void deleteOrder(int orderId);
 public OrderDto updateOrder(int orderId,OrderDto orderDto);

 

}
