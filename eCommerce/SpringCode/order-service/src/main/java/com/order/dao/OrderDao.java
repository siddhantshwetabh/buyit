package com.order.dao;

import com.order.model.Order;
import com.order.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends JpaRepository<Order,Integer>{
	
  Optional<List<Order>>findByUser(User findByEmail);

  @Query(value = "select * from ecom_order where orderId = :orderId", nativeQuery = true)
  Order findByOrderId(int orderId);
  
  
  
  

}
