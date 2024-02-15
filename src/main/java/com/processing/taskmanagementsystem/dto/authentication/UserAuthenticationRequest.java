package com.processing.taskmanagementsystem.dto.authentication;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserAuthenticationRequest implements Serializable {

    @NotNull
    private String username;
    @NotNull
    private String password;
}