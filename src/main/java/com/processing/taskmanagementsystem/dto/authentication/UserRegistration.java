package com.processing.taskmanagementsystem.dto.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistration implements Serializable {

    private String uuid;
    @NotBlank
    @Size(max = 255)
    private String firstName;
    @NotBlank
    @Size(max = 255)
    private String lastName;
    @NotBlank
    @Size(max = 255)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
}