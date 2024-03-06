package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.User;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();

    List<UserResponseDto> getAllUsersWithoutTask();

    List<UserResponseDto> getAllUsersWithTask();

    UserResponseDto findByUsername(String username);

    User findUserByUsername(String username);

    boolean deleteUser(String uuid);
}