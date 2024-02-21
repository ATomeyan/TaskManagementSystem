package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.AlreadyExistException;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.mapper.UserMapper;
import com.processing.taskmanagementsystem.repository.RoleRepository;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserAuthenticationService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserAuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(UserRegistration userRegistration) {

        if (userRegistration == null) {
            LOGGER.error("The registration request is invalid.");
            throw new InvalidObjectException("The registration request is invalid.");
        }

        Optional<User> userByUsername = userRepository.findUserByUsername(userRegistration.getUsername());

        if (userByUsername.isPresent()) {
            LOGGER.error("User by username {} already exist:", userRegistration.getUsername());
            throw new AlreadyExistException(String.format("User by username already exist: %s", userRegistration.getUsername()));
        }

        User user = UserMapper.mapRegistrationRequestToEntity(userRegistration);

        userRepository.save(user);
    }

    @Override
    public UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest) {
        return null;
    }

    private String passwordGenerator() {
        return "";
    }
}