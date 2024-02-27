package com.processing.taskmanagementsystem.dto.request.update.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserUpdateRequestDto implements Serializable {

    private String uuid;
    private String firstName;
    private String lastName;
    private String username;
}