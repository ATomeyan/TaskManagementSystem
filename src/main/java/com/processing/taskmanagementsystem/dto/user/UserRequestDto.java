package com.processing.taskmanagementsystem.dto.user;

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
    private String firstName;
    private String lastName;
}