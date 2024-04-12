package com.processing.taskmanagementsystem.dto.request.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationRequest implements Serializable {

    private String uuid;
    @NotBlank(message = "First name is mandatory")
    @Size(max = 255)
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 255)
    private String lastName;
    @NotBlank(message = "Username is mandatory")
    @Size(max = 255)
    private String username;
    private String password;
    @NotBlank(message = "Role is mandatory")
    private String role;
}