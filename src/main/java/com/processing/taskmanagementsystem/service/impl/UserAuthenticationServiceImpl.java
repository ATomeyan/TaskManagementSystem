package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;
import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.AlreadyExistException;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.mapper.UserMapper;
import com.processing.taskmanagementsystem.repository.RoleRepository;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserAuthenticationService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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

        Role roleByName = roleRepository.findRoleByName(userRegistration.getRole());
        User user = UserMapper.mapRegistrationRequestToEntity(roleByName, userRegistration);
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));

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