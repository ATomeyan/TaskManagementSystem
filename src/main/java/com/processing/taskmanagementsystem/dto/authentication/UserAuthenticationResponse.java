package com.processing.taskmanagementsystem.dto.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
}