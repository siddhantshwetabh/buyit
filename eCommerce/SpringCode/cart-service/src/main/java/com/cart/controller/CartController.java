package com.cart.controller;

import com.cart.dto.CartDTO;
import com.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartService cartService;

    @PostMapping(value = "/addToCart")
    public CartDTO addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        logger.info("Cart Controller - entering addToCart method");
        return cartService.addToCart(userId, productId, quantity);
    }

    @GetMapping("/getCartByUserId")
    public ResponseEntity<CartDTO> getCart(@RequestParam Long userId){
        logger.info("Cart Controller - entering getCart method");
        CartDTO item = this.cartService.getCart(userId);
        return ResponseEntity.ok().body(item);
    }

    @DeleteMapping ("/removeProductByProductId")
    public ResponseEntity<CartDTO> removeProduct(@RequestParam Long userId, @RequestParam Long productId){
        logger.info("Cart Controller - entering removeProduct method");
        CartDTO removeCartItem = this.cartService.removeCartItem(userId, productId);
        return ResponseEntity.ok().body(removeCartItem);
    }
}
