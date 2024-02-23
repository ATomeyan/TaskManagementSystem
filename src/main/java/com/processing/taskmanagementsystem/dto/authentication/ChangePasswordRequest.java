package com.processing.taskmanagementsystem.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ChangePasswordRequest implements Serializable {

    private String uuid;
    @NotBlank
    private String oldPassword;
    @NotBlank
    @Pattern(message = "Password is not valid", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,12}$")
    @Size(min = 8, max = 12)
    private String newPassword;
}