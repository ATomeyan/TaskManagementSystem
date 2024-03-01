package com.processing.taskmanagementsystem.dto.request.update.authentication;

import com.processing.taskmanagementsystem.utils.ValidRegexp;
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
    private String currentPassword;
    @NotBlank
    @Pattern(message = "Password is not valid", regexp = ValidRegexp.password)
    @Size(min = 8, max = 12)
    private String newPassword;
    @NotBlank
    private String confirmNewPassword;
}