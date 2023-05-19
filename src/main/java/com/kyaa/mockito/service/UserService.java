package com.kyaa.mockito.service;

import com.kyaa.mockito.Role;
import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;

import java.util.List;

public interface UserService {
    String saveUser(RegisterUserRequest registerUserRequest);
    User findUserById(Long id);
    List<User> findAllUsers();
    String addRole(Long userId, String role);
}
