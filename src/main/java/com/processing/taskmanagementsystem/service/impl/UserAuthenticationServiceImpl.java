package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.config.jwt.JwtProvider;
import com.processing.taskmanagementsystem.dto.request.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
import com.processing.taskmanagementsystem.dto.response.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.AlreadyExistException;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.mapper.UserMapper;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.RoleService;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserAuthenticationService.class);
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public void createUser(UserRegistrationRequest userRegistration) {

        if (userRegistration == null) {
            LOGGER.error("The registration request is invalid.");
            throw new InvalidObjectException("The registration request is invalid.");
        }

        Optional<User> userByUsername = userRepository.findUserByUsername(userRegistration.getUsername());

        if (userByUsername.isPresent()) {
            LOGGER.error("User by username {} already exist:", userRegistration.getUsername());
            throw new AlreadyExistException(String.format("User by username already exist: %s", userRegistration.getUsername()));
        }

        Role roleByName = roleService.getRoleByName(userRegistration.getRole());
        User user = UserMapper.mapRegistrationRequestToEntity(roleByName, userRegistration);
        user.setPassword(passwordEncoder.encode(passwordGenerator()));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest) {

        if (userAuthenticationRequest == null) {
            LOGGER.error("Authentication request {} is invalid.", userAuthenticationRequest);
            throw new InvalidObjectException(String.format("Authentication request {} is invalid. %s", userAuthenticationRequest));
        }

        Optional<User> userByUsername = userRepository.findUserByUsername(userAuthenticationRequest.getUsername());

        if (userByUsername.isPresent() && userByUsername.get().isEnabled()) {

            String accessToken = jwtProvider.accessTokenGenerator(userByUsername.get());
            String refreshToken = jwtProvider.refreshTokenGenerator(userByUsername.get());

            if (passwordEncoder.matches(userAuthenticationRequest.getPassword(), userByUsername.get().getPassword())) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userAuthenticationRequest.getUsername(), userAuthenticationRequest.getPassword()));
            }

            return UserAuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        }

        return null;
    }

    private String passwordGenerator() {
        Random random = new SecureRandom();
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            password.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return new String(password);
    }
}