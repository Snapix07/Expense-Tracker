package com.snapix.expensetracker.mapper;

import com.snapix.expensetracker.dto.user.UserRequestDTO;
import com.snapix.expensetracker.dto.user.UserResponseDTO;
import com.snapix.expensetracker.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO dto){
        return new User()
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setEmail(dto.getEmail());
    }

    public static UserResponseDTO toDto(User entity){
        return new UserResponseDTO()
                .setId(entity.getId())
                .setUsername(entity.getUsername())
                .setEmail(entity.getEmail());
    }
}