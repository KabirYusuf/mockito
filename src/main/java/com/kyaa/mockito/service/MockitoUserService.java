package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;
import com.kyaa.mockito.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockitoUserService implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public String saveUser(RegisterUserRequest registerUserRequest) {
        User user = new User(null,"kabir@gmail.com","1234");
        userRepository.save(user);
        return "Success";
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
