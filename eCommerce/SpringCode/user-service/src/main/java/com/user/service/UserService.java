package com.user.service;

import com.user.dto.UserDTO;
import com.user.entity.User;

public interface UserService {

    User registerNewUser(UserDTO user);

    User loginUser(String emailId, String password);

    UserDTO getUser(Long userId);

}
