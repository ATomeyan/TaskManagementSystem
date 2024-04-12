package com.processing.taskmanagementsystem.dto.request.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserAuthenticationRequest implements Serializable {

    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
}