package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.mapper.UserMapper;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto findByUsername(String username) {

        if (username == null || username.isBlank()) {
            LOGGER.error("The username is empty.");
        }

        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        UserResponseDto userResponseDto = null;

        if (userByUsername.isPresent()) {
            userResponseDto = UserMapper.mapEntityToUserResponse(userByUsername.get());
        }

        return userResponseDto;
    }
}