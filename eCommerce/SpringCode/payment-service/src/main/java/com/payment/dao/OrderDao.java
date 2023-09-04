package com.payment.dao;

import com.payment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDao extends JpaRepository<Order,Integer> {
    @Query(value = "Select * from ecom_order where order_id = :orderId",nativeQuery = true)
    Order checkOrderId(int orderId);
}
