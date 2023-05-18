package com.kyaa.mockito.controller;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.service.MockitoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/")
@RequiredArgsConstructor
public class UserController {
    private final MockitoUserService mockitoUserService;
    @PostMapping("register")
    public String registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        return mockitoUserService.saveUser(registerUserRequest);
    }
}
