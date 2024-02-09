package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserAuthenticationService.class);
    private final UserRepository userRepository;

    public UserAuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserRegistration userRegistration) {

    }

    @Override
    public UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest) {
        return null;
    }
}