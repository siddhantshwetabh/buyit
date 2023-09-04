package com.cart.serviceimpl;

import com.cart.dao.CartDAO;
import com.cart.dao.ProductDAO;
import com.cart.dao.UserDao;
import com.cart.dto.CartDTO;
import com.cart.service.CartService;
import com.cart.entities.Cart;
import com.cart.entities.CartItem;
import com.cart.entities.Product;
import com.cart.entities.User;
import com.cart.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    CartDAO cartDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    UserDao userDao;

    @Autowired
    ModelMapper mapper;

    @Override
    public Cart findCartById(Long cartId) {
        return cartDAO.getCartById(cartId);
    }

    @Override
    public CartDTO addToCart(Long userId, Long productId, int quantity) {
        logger.info("CartServiceImpl - entering addToCart method..");
        User user = userDao.findByUserId(userId);
        Product product = productDAO.getByProductId(productId);
        if (product.getStock()< quantity) {
            throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Stock is out of Stock");
        }
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalProductPrize();
        Cart cart = user.getCart();

        if(cart==null) {
            cart=new Cart();
            cart.setUser(user);
        }

        cartItem.setCart(cart);
        Set<CartItem> items = cart.getIteam();

        AtomicReference<Boolean> flag=new AtomicReference<>(false);
        Set<CartItem> newitem= items.stream().map(i -> {

            if(Objects.equals(i.getProduct().getProductId(), product.getProductId())) {

                i.setQuantity(quantity);
                i.setTotalProductPrize();
                flag.set(true);
            }
            return i;
        }).collect(Collectors.toSet());

        if(Boolean.TRUE.equals(flag.get())) {
            items.clear();
            items.addAll(newitem);
        }
        else {
            cartItem.setCart(cart);
            items.add(cartItem);
        }
        Cart updateCart = this.cartDAO.save(cart);

        return this.mapper.map(updateCart,CartDTO.class);

    }
    @Override
    public CartDTO getCart(long userId) {
        logger.info("CartServiceImpl - entering getCart method..");
        User user = this.userDao.findByUserId(userId);
        Optional<Cart> cart =  this.cartDAO.findByUser(user);
        return this.mapper.map(cart,CartDTO.class) ;
    }
    @Override
    public CartDTO removeCartItem(long userId, long productId) {
        logger.info("CartServiceImpl - entering removeCartItem method..");
        User user = this.userDao.findByUserId(userId);
        Cart cart=user.getCart();
        Set<CartItem> iteam = cart.getIteam();
        iteam.removeIf(item ->item.getProduct().getProductId() ==productId);
        Cart updatecart = this.cartDAO.save(cart);

        return this.mapper.map(updatecart,CartDTO.class);
    }

}