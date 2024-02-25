package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.authentication.ChangePasswordRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;
import com.processing.taskmanagementsystem.dto.user.UserResponseDto;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import com.processing.taskmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserAuthenticationService userAuthenticationService;
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserAuthenticationService userAuthenticationService, UserService userService,
                          UserRepository userRepository) {
        this.userAuthenticationService = userAuthenticationService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> allUsers = userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/without-task")
    public ResponseEntity<List<UserResponseDto>> getAllUsersWithoutTask() {
        List<UserResponseDto> allUsersWithoutTask = userService.getAllUsersWithoutTask();

        return new ResponseEntity<>(allUsersWithoutTask, HttpStatus.OK);
    }

    @GetMapping("/with-task")
    public ResponseEntity<List<UserResponseDto>> getAllUsersWithTask() {
        List<UserResponseDto> allUsersWithTask = userService.getAllUsersWithTask();

        return new ResponseEntity<>(allUsersWithTask, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegistration userRegistration) {
        userAuthenticationService.createUser(userRegistration);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationResponse> login(@RequestBody @Valid UserAuthenticationRequest userAuthenticationRequest) {

        UserAuthenticationResponse authenticationResponse = userAuthenticationService.login(userAuthenticationRequest);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteUser(@PathVariable String uuid) {
        userRepository.deleteById(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}