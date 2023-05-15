package com.kyaa.mockito.data.dto.request;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
}
