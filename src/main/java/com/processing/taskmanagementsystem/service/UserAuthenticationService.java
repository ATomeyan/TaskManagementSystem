package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
import com.processing.taskmanagementsystem.dto.response.authentication.UserAuthenticationResponse;

public interface UserAuthenticationService {
    void createUser(UserRegistrationRequest userRegistration);

    UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest);
}