package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;
import com.kyaa.mockito.data.repository.UserRepository;
import com.kyaa.mockito.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockitoUserService implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String saveUser(RegisterUserRequest registerUserRequest) {
        if (userRepository.findUserByEmail(registerUserRequest.getEmail()).isPresent())throw  new UserException("Email exist");
        User user = new User(null,registerUserRequest.getEmail(),registerUserRequest.getPassword());
        userRepository.save(user);
        return "Success";
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
