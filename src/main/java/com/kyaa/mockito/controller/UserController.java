package com.kyaa.mockito.controller;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;
import com.kyaa.mockito.service.MockitoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final MockitoUserService mockitoUserService;
    @PostMapping("register")
    public String registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        return mockitoUserService.saveUser(registerUserRequest);
    }
    @GetMapping("get-all-users")
    public List<User> findAllUsers(){
        return mockitoUserService.findAllUsers();
    }
}
