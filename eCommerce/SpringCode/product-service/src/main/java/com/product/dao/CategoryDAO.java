package com.product.dao;

import com.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

    @Query(value = "select * from category where user_id = :userId", nativeQuery = true)
    Category getById(Long userId);

}
