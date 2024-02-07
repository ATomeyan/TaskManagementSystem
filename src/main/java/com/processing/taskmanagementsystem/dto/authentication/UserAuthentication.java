package com.processing.taskmanagementsystem.dto.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserAuthentication implements Serializable {

    private String username;
    private String password;
}