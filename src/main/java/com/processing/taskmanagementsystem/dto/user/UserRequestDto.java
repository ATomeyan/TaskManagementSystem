package com.processing.taskmanagementsystem.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserRequestDto implements Serializable {

    private UUID uuid;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String username;
}