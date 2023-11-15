package com.ufmg.streaming.API.Streaming.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@example.com";
        User user = User.builder().build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(user, result);

        // Verifica se o método findByEmail foi chamado no repositório
        verify(userRepository, times(1)).findByEmail(eq(email));
    }

    @Test
    public void testGetUserByEmailNotFound() {
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        User result = userService.getUserByEmail(email);

        assertNull(result);

        // Verifica se o método findByEmail foi chamado no repositório
        verify(userRepository, times(1)).findByEmail(eq(email));
    }

    @Test
    public void testGetUserById() {
        Integer idUser = 1;
        User user = User.builder().build();

        when(userRepository.findById(idUser)).thenReturn(Optional.of(user));

        User result = userService.getUserById(idUser);

        assertNotNull(result);
        assertEquals(user, result);

        // Verifica se o método findById foi chamado no repositório
        verify(userRepository, times(1)).findById(eq(idUser));
    }

    @Test
    public void testGetUserByIdNotFound() {
        Integer idUser = 1;

        when(userRepository.findById(idUser)).thenReturn(Optional.empty());

        User result = userService.getUserById(idUser);

        assertNull(result);

        // Verifica se o método findById foi chamado no repositório
        verify(userRepository, times(1)).findById(eq(idUser));
    }

    @Test
    public void testSaveUser() {
        User user = User.builder().build();

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(user, result);

        // Verifica se o método save foi chamado no repositório
        verify(userRepository, times(1)).save(eq(user));
    }

    @Test
    public void testDeleteUser() {
        User user = User.builder().build();

        userService.deleteUser(user);

        // Verifica se o método delete foi chamado no repositório
        verify(userRepository, times(1)).delete(eq(user));
    }
}
