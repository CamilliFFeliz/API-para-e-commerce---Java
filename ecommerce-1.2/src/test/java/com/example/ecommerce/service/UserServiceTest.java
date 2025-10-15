
package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, encoder);
    }

    @Test
    public void register_success() {
        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User u = userService.register("newuser", "password");
        assertNotNull(u.getPassword());
        assertTrue(encoder.matches("password", u.getPassword()));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void register_alreadyExists() {
        when(userRepository.findByUsername("exists")).thenReturn(Optional.of(new User()));
        Exception ex = assertThrows(RuntimeException.class, () -> userService.register("exists", "x"));
        assertNotNull(ex.getMessage());
    }
}
