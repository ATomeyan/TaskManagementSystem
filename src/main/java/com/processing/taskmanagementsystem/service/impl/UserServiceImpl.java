package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.mapper.UserMapper;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        if (allUsers.isEmpty()) {
            LOGGER.error("User not found.");
            throw new NotFoundException("User not found.");
        }

        return allUsers.stream().map(UserMapper::mapEntityToUserResponse).toList();
    }

    @Override
    public List<UserResponseDto> getAllUsersWithoutTask() {

        List<User> allUsers = userRepository.findAll();

        if (allUsers.isEmpty()) {
            LOGGER.error("User not found.");
            throw new NotFoundException("User not found.");
        }

        List<UserResponseDto> usersWithoutTask = allUsers
                .stream()
                .filter(user -> user.getTaskUsers().stream().iterator().next().getTask() == null)
                .map(UserMapper::mapEntityToUserResponse)
                .toList();

        if (usersWithoutTask.isEmpty()) {
            LOGGER.error("User without task was not found.");
            throw new NotFoundException("User without task was not found.");
        }

        return usersWithoutTask;
    }

    @Override
    public List<UserResponseDto> getAllUsersWithTask() {
        List<User> allUsers = userRepository.findAll();

        if (allUsers.isEmpty()) {
            LOGGER.error("User not found.");
            throw new NotFoundException("User not found.");
        }

        List<UserResponseDto> usersWithTask = allUsers
                .stream()
                .filter(u -> u.getTaskUsers().stream().iterator().next().getTask() != null)
                .map(UserMapper::mapEntityToUserResponse)
                .toList();

        if (usersWithTask.isEmpty()) {
            LOGGER.error("User with assigned task was not found.");
            throw new NotFoundException("User with assigned task was not found.");
        }

        return usersWithTask;
    }

    @Override
    @Transactional
    public UserResponseDto findByUsername(String username) {

        checkUsername(username);

        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        UserResponseDto userResponseDto = null;

        if (userByUsername.isPresent()) {
            userResponseDto = UserMapper.mapEntityToUserResponse(userByUsername.get());
        }

        return userResponseDto;
    }

    @Override
    public boolean deleteUser(String uuid) {

        Optional<User> byId = userRepository.findById(uuid);

        if (byId.isPresent()) {
            userRepository.deleteById(uuid);
            LOGGER.info("User by {} uid was deleted.", uuid);
            return true;
        } else {
            LOGGER.error("User by {} uid was not found.", uuid);
            throw new NotFoundException(String.format("User by {} uid was not found. %s", uuid));
        }
    }

    private void checkUsername(String username) {
        if (username == null || username.isBlank()) {
            LOGGER.error("The username is empty.");
        }
    }
}