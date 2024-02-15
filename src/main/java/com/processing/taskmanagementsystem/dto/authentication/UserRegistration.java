package com.processing.taskmanagementsystem.dto.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistration implements Serializable {

    private UUID uuid;
    @NotNull
    @Size(max = 255)
    private String firstName;
    @NotNull
    @Size(max = 255)
    private String lastName;
    @NotNull
    @Size(max = 255)
    private String username;
    @NotNull
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,12}$")
    @Size(min = 8, max = 12)
    private String password;
}