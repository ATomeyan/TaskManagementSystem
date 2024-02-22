package com.processing.taskmanagementsystem.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserAuthenticationRequest implements Serializable {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}