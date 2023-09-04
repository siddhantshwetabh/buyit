package com.user.serviceimpl;

import com.user.dto.UserDTO;
import com.user.dao.UserDao;
import com.user.entity.User;
import com.user.exception.ResourceServerException;
import com.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    public User registerNewUser(UserDTO user) {
        logger.info("UserServiceImpl-registerNewUser:Entering registerNewUser method");
        User user1=userDao.getUserByEmailId(user.getEmailId());
        User user2=userDao.getUserByPhNo(user.getPhNo());
        if(user1 == null && user2 == null){
            User user3 = new User();
            user3.setUserFirstName(user.getUserFirstName());
            user3.setUserLastName(user.getUserLastName());
            user3.setUserPassword(user.getUserPassword());
            user3.setEmailId(user.getEmailId());
            user3.setPhNo(user.getPhNo());
            userDao.save(user3);
            return userDao.getUserByEmailId(user.getEmailId());
        }
        else if (user1 != null && user2 != null){
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email_Id and Phone Number already exist in db!!");
        }
        else if (user1 != null){
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email_Id already exist in db!!");
        }
        else{
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Phone Number already exist in db!!");
        }
    }
    public User loginUser(String emailId, String password) {
        logger.info("UserServiceImpl-loginUser:Entering loginUser method");
        User user=userDao.getUserByEmailId(emailId);
        if(user != null) {
            if (user.getUserPassword().equals(password)) {
                return user;
            } else {
                throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Wrong email id and password! Please enter correct credentials.");
            }
        }
        else {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Wrong email id and password! Please enter correct credentials.");
        }
        }


    public UserDTO getUser(Long userId) {
        logger.info("UserServiceImpl-getUser:Entering getUser method");
        User user = userDao.findByUserId(userId);

        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUserFirstName(user.getUserFirstName());
            userDTO.setUserLastName(user.getUserLastName());
            userDTO.setEmailId(user.getEmailId());
            userDTO.setPhNo(user.getPhNo());
            return userDTO;
        } else {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User doesn't exists");
        }
    }

    }
