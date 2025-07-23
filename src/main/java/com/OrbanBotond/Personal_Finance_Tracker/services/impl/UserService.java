package com.OrbanBotond.Personal_Finance_Tracker.services.impl;

import com.OrbanBotond.Personal_Finance_Tracker.dto.UserRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.UserResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;
import com.OrbanBotond.Personal_Finance_Tracker.mappers.UserMapper;
import com.OrbanBotond.Personal_Finance_Tracker.repositories.UserRepository;
import com.OrbanBotond.Personal_Finance_Tracker.services.IUserService;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User newUser = UserMapper.newUser(userRequestDTO);
        return UserMapper.toDTO(userRepository.save(newUser));
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long userId) {
        User updateUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        updateUser = UserMapper.toEntity(updateUser, userRequestDTO);
        updateUser = userRepository.save(updateUser);
        return UserMapper.toDTO(updateUser);
    }

    @Override
    public UserResponseDTO getUser(Long userId) {
        User getUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(getUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(UserMapper.toDTO(user)));
        return users;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
