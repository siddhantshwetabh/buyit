
package com.cart.dao;

import com.cart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where category_Id = :categoryId", nativeQuery = true)
    List<Product> getProductByCategoryId(long categoryId);

    @Query(value = "select * from product where product_Id = :productId", nativeQuery = true)
    Product getByProductId(long productId);
}

