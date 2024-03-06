package com.processing.taskmanagementsystem.dto.request.update.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateRequest implements Serializable {

    private String uuid;
    private String firstName;
    private String lastName;
    private String username;
    private String role;
}