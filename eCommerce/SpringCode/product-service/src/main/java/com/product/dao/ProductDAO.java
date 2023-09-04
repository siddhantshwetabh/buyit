package com.product.dao;

import com.product.model.Category;
import com.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where category_Id = :categoryId", nativeQuery = true)
    List<Product> getProductByCategoryId(long categoryId);

    @Query(value = "select * from product where user_id = :userId", nativeQuery = true)
    Product getById(Long userId);
}
