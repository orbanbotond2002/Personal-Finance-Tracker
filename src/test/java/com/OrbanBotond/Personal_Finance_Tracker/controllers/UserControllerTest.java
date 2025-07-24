package com.OrbanBotond.Personal_Finance_Tracker.controllers;

import com.OrbanBotond.Personal_Finance_Tracker.dto.UserRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.UserResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private IUserService userService;
    private UserController userController;

    @BeforeEach
    public void setUp() {
        userService = mock(IUserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void createUser_Success() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Test User");
        requestDTO.setEmail("test@example.com");

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Test User");
        responseDTO.setEmail("test@example.com");

        when(userService.createUser(any(UserRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<?> response = userController.createUser(requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserResponseDTO);
        UserResponseDTO body = (UserResponseDTO) response.getBody();
        assertEquals(1L, body.getId());
        assertEquals("Test User", body.getName());
        assertEquals("test@example.com", body.getEmail());
    }

    @Test
    public void createUser_Conflict() {
        UserRequestDTO requestDTO = new UserRequestDTO();

        when(userService.createUser(any(UserRequestDTO.class)))
                .thenThrow(new RuntimeException("User already exists"));

        ResponseEntity<?> response = userController.createUser(requestDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("User already exists", response.getBody());
    }

    @Test
    public void getUser_Success() {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Test User");
        responseDTO.setEmail("test@example.com");

        when(userService.getUser(1L)).thenReturn(responseDTO);

        ResponseEntity<?> response = userController.getUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserResponseDTO);
    }

    @Test
    public void getUser_NotFound() {
        when(userService.getUser(1L)).thenThrow(new RuntimeException("User not found"));

        ResponseEntity<?> response = userController.getUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void getAllUsers_Success() {
        UserResponseDTO user1 = new UserResponseDTO();
        user1.setId(1L);
        user1.setName("User 1");
        user1.setEmail("user1@example.com");

        UserResponseDTO user2 = new UserResponseDTO();
        user2.setId(2L);
        user2.setName("User 2");
        user2.setEmail("user2@example.com");

        when(userService.getAllUsers()).thenReturn(List.of(user1, user2));

        ResponseEntity<List<UserResponseDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void updateUser_Success() {
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Updated Name");
        requestDTO.setEmail("updated@example.com");

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Updated Name");
        responseDTO.setEmail("updated@example.com");

        when(userService.updateUser(any(UserRequestDTO.class), eq(1L))).thenReturn(responseDTO);

        ResponseEntity<?> response = userController.updateUser(1L, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserResponseDTO);
        UserResponseDTO body = (UserResponseDTO) response.getBody();
        assertEquals("Updated Name", body.getName());
    }

    @Test
    public void updateUser_NotFound() {
        UserRequestDTO requestDTO = new UserRequestDTO();

        when(userService.updateUser(any(UserRequestDTO.class), eq(1L)))
                .thenThrow(new RuntimeException("User not found"));

        ResponseEntity<?> response = userController.updateUser(1L, requestDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }

    @Test
    public void deleteUser_Success() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<?> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void deleteUser_NotFound() {
        doThrow(new RuntimeException("User not found")).when(userService).deleteUser(1L);

        ResponseEntity<?> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}