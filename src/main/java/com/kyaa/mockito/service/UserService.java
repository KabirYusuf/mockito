package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;

import java.util.List;

public interface UserService {
    void saveUser(RegisterUserRequest registerUserRequest);
    User findUserById(Long id);
    List<User> findAllUsers();
}
