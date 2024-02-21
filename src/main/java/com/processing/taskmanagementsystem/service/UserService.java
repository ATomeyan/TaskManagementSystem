package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto findByUsername(String username);
}