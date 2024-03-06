package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
import com.processing.taskmanagementsystem.dto.request.update.authentication.ChangePasswordRequest;
import com.processing.taskmanagementsystem.dto.request.update.authentication.UserUpdateRequest;
import com.processing.taskmanagementsystem.dto.response.authentication.UserAuthenticationResponse;

import java.security.Principal;

public interface UserAuthenticationService {
    void createUser(UserRegistrationRequest userRegistration);

    UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest);

    void updateUser(UserUpdateRequest userUpdateRequest);

    void changePassword(ChangePasswordRequest changePasswordRequest, Principal principal);
}