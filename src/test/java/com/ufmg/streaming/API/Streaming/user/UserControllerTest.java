package com.ufmg.streaming.API.Streaming.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        when(userService.getUserByEmail(email)).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.getUserByEmail(email);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());

        // Verifica se o método getUserByEmail foi chamado no serviço
        verify(userService, times(1)).getUserByEmail(eq(email));
    }

    @Test
    public void testEditUser() {
        Integer idUser = 1;
        User updatedUser = new User();
        User existingUser = new User();

        when(userService.getUserById(idUser)).thenReturn(existingUser);
        when(userService.saveUser(existingUser)).thenReturn(updatedUser);

        ResponseEntity<User> responseEntity = userController.editUser(idUser, updatedUser);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, responseEntity.getBody());

        // Verifica se o método getUserById e saveUser foram chamados no serviço
        verify(userService, times(1)).getUserById(eq(idUser));
        verify(userService, times(1)).saveUser(eq(existingUser));
    }

    @Test
    public void testDeleteUser() {
        Integer idUser = 1;
        User existingUser = new User();

        when(userService.getUserById(idUser)).thenReturn(existingUser);

        ResponseEntity<String> responseEntity = userController.deleteUser(idUser);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Usuário deletado com sucesso!", responseEntity.getBody());

        // Verifica se o método getUserById e deleteUser foram chamados no serviço
        verify(userService, times(1)).getUserById(eq(idUser));
        verify(userService, times(1)).deleteUser(eq(existingUser));
    }

    @Test
    public void testGetUserByEmailNotFound() {
        String email = "nonexistent@example.com";
        when(userService.getUserByEmail(email)).thenReturn(null);

        ResponseEntity<User> responseEntity = userController.getUserByEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verifica se o método getUserByEmail foi chamado no serviço
        verify(userService, times(1)).getUserByEmail(eq(email));
    }

    @Test
    public void testEditUserNotFound() {
        Integer idUser = 1;
        User updatedUser = new User();

        when(userService.getUserById(idUser)).thenReturn(null);

        ResponseEntity<User> responseEntity = userController.editUser(idUser, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verifica se o método getUserById foi chamado no serviço
        verify(userService, times(1)).getUserById(eq(idUser));
        // Verifica se o método saveUser não foi chamado
        verify(userService, never()).saveUser(any());
    }

    @Test
    public void testDeleteUserNotFound() {
        Integer idUser = 1;

        when(userService.getUserById(idUser)).thenReturn(null);

        ResponseEntity<String> responseEntity = userController.deleteUser(idUser);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verifica se o método getUserById foi chamado no serviço
        verify(userService, times(1)).getUserById(eq(idUser));
        // Verifica se o método deleteUser não foi chamado
        verify(userService, never()).deleteUser(any());
    }
}
