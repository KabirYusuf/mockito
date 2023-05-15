package com.kyaa.mockito.service;

import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;
import com.kyaa.mockito.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockitoUserServiceTest {
    @InjectMocks
    private MockitoUserService mockitoUserService;
    @Mock
    private UserRepository userRepository;
    private RegisterUserRequest registerUserRequest;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    void setUp(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("kabir@gmail.com");
        registerUserRequest.setPassword("1234");
    }

    @Test
    void saveUser() {
        User savedUser = new User(1L,"kabir@gmail.com","1234");
//        when(userRepository.save(userArgumentCaptor.capture())).thenReturn(savedUser);
//        mockitoUserService.saveUser(registerUserRequest);
//        User user = userArgumentCaptor.getValue();
//        assertEquals("kabir@gmail.com", user.getEmail());
//        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
//        verifyNoMoreInteractions(userRepository);
        given(userRepository.save(userArgumentCaptor.capture())).willReturn(savedUser);

        String response = mockitoUserService.saveUser(registerUserRequest);

        then(response).isEqualTo("Success");

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findUserById() {
    }

    @Test
    void findAllUsers() {
    }
}