package com.processing.taskmanagementsystem.dto.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserRegistration implements Serializable {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}