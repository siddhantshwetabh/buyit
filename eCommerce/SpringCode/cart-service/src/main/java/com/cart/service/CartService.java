package com.cart.service;

import com.cart.dto.CartDTO;
import com.cart.entities.Cart;

public interface CartService {

    Cart findCartById(Long cartId);

    CartDTO addToCart(Long userId, Long productId, int quantity);


    CartDTO getCart(long userId);

    CartDTO  removeCartItem(long userId,long productId);




}
