package com.OrbanBotond.Personal_Finance_Tracker.mappers;

import com.OrbanBotond.Personal_Finance_Tracker.dto.UserRequestDTO;
import com.OrbanBotond.Personal_Finance_Tracker.dto.UserResponseDTO;
import com.OrbanBotond.Personal_Finance_Tracker.entities.User;

public class UserMapper {
    public static User newUser(UserRequestDTO dto) {
        User user = new User();
        return toEntity(user, dto);
    }

    public static User toEntity(User user, UserRequestDTO dto) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
