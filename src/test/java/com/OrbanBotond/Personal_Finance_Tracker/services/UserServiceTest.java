package com.OrbanBotond.Personal_Finance_Tracker.services;

import com.OrbanBotond.Personal_Finance_Tracker.dto.UserRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.UserResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;
import com.OrbanBotond.Personal_Finance_Tracker.mappers.UserMapper;
import com.OrbanBotond.Personal_Finance_Tracker.repositories.UserRepository;
import com.OrbanBotond.Personal_Finance_Tracker.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_SaveAndReturnUserResponseDTO(){
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Test User");
        requestDTO.setEmail("test@example.com");

        User savedUser = UserMapper.newUser(requestDTO);
        savedUser.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponseDTO responseDTO = userService.createUser(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.getId());
        assertEquals("Test User", responseDTO.getName());
        assertEquals("test@example.com", responseDTO.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void updateUser_WhenUserExists_ShouldUpdateAndReturnDTO() {
        Long userId = 1L;
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setName("Updated Name");
        requestDTO.setEmail("updated@example.com");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("Old Name");
        existingUser.setEmail("old@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponseDTO responseDTO = userService.updateUser(requestDTO, userId);

        assertNotNull(responseDTO);
        assertEquals(userId, responseDTO.getId());
        assertEquals("Updated Name", responseDTO.getName());
        assertEquals("updated@example.com", responseDTO.getEmail());

        verify(userRepository).findById(userId);
        verify(userRepository).save(existingUser);
    }

    @Test
    void updateUser_WhenUserNotFound_ShouldThrowException() {
        Long userId = 99L;
        UserRequestDTO requestDTO = new UserRequestDTO();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(requestDTO, userId);
        });

        assertEquals("User not found", ex.getMessage());

        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any());
    }

    @Test
    void getUser_WhenUserExists_ShouldReturnDTO() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("Test User");
        user.setEmail("test@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResponseDTO dto = userService.getUser(userId);

        assertNotNull(dto);
        assertEquals(userId, dto.getId());
        assertEquals("Test User", dto.getName());
        assertEquals("test@example.com", dto.getEmail());

        verify(userRepository).findById(userId);
    }

    @Test
    void getUser_WhenUserNotFound_ShouldThrowException() {
        Long userId = 99L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.getUser(userId);
        });

        assertEquals("User not found", ex.getMessage());

        verify(userRepository).findById(userId);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUserResponseDTO() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("User One");
        user1.setEmail("one@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User Two");
        user2.setEmail("two@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserResponseDTO> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("User One", users.get(0).getName());
        assertEquals("User Two", users.get(1).getName());

        verify(userRepository).findAll();
    }

    @Test
    void deleteUser_ShouldCallDeleteById() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}
