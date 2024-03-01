package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.request.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
import com.processing.taskmanagementsystem.dto.request.update.authentication.ChangePasswordRequest;
import com.processing.taskmanagementsystem.dto.response.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import com.processing.taskmanagementsystem.service.UserAuthenticationService;
import com.processing.taskmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserAuthenticationService userAuthenticationService;
    private final UserService userService;

    public UserController(UserAuthenticationService userAuthenticationService, UserService userService) {
        this.userAuthenticationService = userAuthenticationService;
        this.userService = userService;
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
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegistrationRequest userRegistration) {
        userAuthenticationService.createUser(userRegistration);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthenticationResponse> login(@RequestBody @Valid UserAuthenticationRequest userAuthenticationRequest) {

        UserAuthenticationResponse authenticationResponse = userAuthenticationService.login(userAuthenticationRequest);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest passwordRequest, Principal principal) {
        userAuthenticationService.changePassword(passwordRequest, principal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteUser(@PathVariable String uuid) {
        boolean deleteUser = userService.deleteUser(uuid);

        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }
}