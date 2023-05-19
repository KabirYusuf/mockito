package com.kyaa.mockito.service;

import com.kyaa.mockito.Role;
import com.kyaa.mockito.data.dto.request.RegisterUserRequest;
import com.kyaa.mockito.data.model.User;
import com.kyaa.mockito.data.repository.UserRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.kyaa.mockito.Role.ADMIN;
import static com.kyaa.mockito.Role.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        User savedUser = new User(1L,"kabir@gmail.com","1234", Set.of(USER));
//        when(userRepository.save(userArgumentCaptor.capture())).thenReturn(savedUser);
//        mockitoUserService.saveUser(registerUserRequest);
//        User user = userArgumentCaptor.getValue();
//        assertEquals("kabir@gmail.com", user.getEmail());
//        verify(userRepository, times(1)).save(userArgumentCaptor.capture());
//        verifyNoMoreInteractions(userRepository);
        given(userRepository.save(userArgumentCaptor.capture())).willReturn(savedUser);

        given(userRepository.findUserByEmail("kabir@gmail.com")).willReturn(Optional.empty());

        String response = mockitoUserService.saveUser(registerUserRequest);

//        then(response).isEqualTo("Success");


        then(userRepository).should().save(userArgumentCaptor.capture());
        BDDAssertions.then(response).isNotNull();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("throw exception when a user tries to register with an email address that already exists")
    void exceptionForUserTryingToRegisterWithEmailAlreadyExisting(){
        given(userRepository.findUserByEmail("kabir@gmail.com")).willThrow(new IllegalStateException("Email exist"));
        assertThrows(IllegalStateException.class, ()->mockitoUserService.saveUser(registerUserRequest));
    }

    @Test
    void findUserById() {
        User user = new User(1L,"bcscnks@gmail.com","hrugn", Set.of(USER));

        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));

        User foundUser = mockitoUserService.findUserById(1L);

        then(userRepository).should().findById(1L);

        BDDAssertions.then(foundUser).isEqualTo(user);
    }

    @Test
    void findAllUsers() {
        given(userRepository.findAll()).willReturn(List.of(
                new User(1L, "ngsnvk","fnacnsl ", Set.of(USER)),
                new User(2L, "nwgbvdn", "huefvbcn", Set.of(USER))
        ));

        var foundUsers = mockitoUserService.findAllUsers();

        then(userRepository).should().findAll();

        BDDAssertions.then(foundUsers.get(0).getId()).isEqualTo(1L);

        verifyNoMoreInteractions(userRepository);
    }
    @Test
    void testToAddRoleToUser(){
        Set<Role> userRole = new HashSet<>();
        userRole.add(USER);
        User user = new User(1L,"bcscnks@gmail.com","hrugn", userRole);

        user.getRoles().add(ADMIN);
        User savedUser = new User(1L,"bcscnks@gmail.com","hrugn", userRole);
        given(userRepository.findById(1L)).willReturn(Optional.of(user));
        given(userRepository.save(user)).willReturn(savedUser);

        String response = mockitoUserService.addRole(1L, "Admin");

        BDDMockito.then(userRepository).should().findById(1L);
        BDDMockito.then(userRepository).should().save(savedUser);

        InOrder inOrder = Mockito.inOrder(userRepository);

        BDDMockito.then(userRepository).should(inOrder).findById(1L);
        BDDMockito.then(userRepository).should(inOrder).save(user);

    }

}