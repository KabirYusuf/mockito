package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockitoUserService implements UserService{
    @Override
    public void saveUser(RegisterUserRequest registerUserRequest) {

    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
