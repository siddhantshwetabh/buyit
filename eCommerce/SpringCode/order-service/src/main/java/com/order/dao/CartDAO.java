package com.order.dao;

import com.order.model.Cart;
import com.order.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartDAO extends JpaRepository<Cart, Integer> {

    @Query(value = "select * from cart where cart_Id = :cartId", nativeQuery = true)
    Cart getCartById(Long cartId);

    Optional<Cart> findByUser(User user);
}
