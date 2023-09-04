package com.cart.dao;

import com.cart.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    /*@Query(value = "select * from user where email_id = :emailId", nativeQuery = true)
    User getUserByEmailId(String emailId);

    @Query(value = "select * from user where ph_no = :phNo", nativeQuery = true)
    User getUserByPhNo(String phNo);*/

    User findByUserId(Long userId);

}
