package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationRequest;
import com.processing.taskmanagementsystem.dto.authentication.UserAuthenticationResponse;
import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;

public interface UserAuthenticationService {
    void createUser(UserRegistration userRegistration);

    UserAuthenticationResponse login(UserAuthenticationRequest userAuthenticationRequest);
}