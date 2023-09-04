package com.user.controller;

import com.user.dto.UserDTO;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.serviceimpl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody UserDTO user) {
        logger.info("User Controller - entering registerNewUser method..");
        return userService.registerNewUser(user);
    }

    @GetMapping("/loginUser")
    public User loginUser(@RequestParam String emailId,@RequestParam String password) {
        logger.info("User Controller - entering loginUser method..");
        return userService.loginUser(emailId,password);
    }

    @GetMapping(value = "/getUserById/{userId}")
    public @ResponseBody
    ResponseEntity<UserDTO> getUsersById(@PathVariable("userId") Long userId) {
        logger.info("UserController - Entering getUsersById method");
        UserDTO list = userService.getUser(userId);
        if (list == null) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "User not found");
        }
        return ResponseEntity.ok().body(list);
    }




}
