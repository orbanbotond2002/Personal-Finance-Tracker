package com.OrbanBotond.Personal_Finance_Tracker.services;

import com.OrbanBotond.Personal_Finance_Tracker.dto.UserRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long userId);
    UserResponseDTO getUser(Long userId);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(Long userId);
}
