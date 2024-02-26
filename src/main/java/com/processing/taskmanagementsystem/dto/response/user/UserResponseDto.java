package com.processing.taskmanagementsystem.dto.response.user;

import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserResponseDto implements Serializable {

    private String uuid;
    private String firstName;
    private String lastName;
    private String username;
    private TaskResponseDto taskResponse;
}