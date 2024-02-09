package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserAuthenticationService userService;

    public UserController(UserAuthenticationService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegistration userRegistration) {
        userService.createUser(userRegistration);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationResponse> login(@RequestBody @Valid UserAuthenticationRequest userAuthenticationRequest) {

        UserAuthenticationResponse authenticationResponse = userService.login(userAuthenticationRequest);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}