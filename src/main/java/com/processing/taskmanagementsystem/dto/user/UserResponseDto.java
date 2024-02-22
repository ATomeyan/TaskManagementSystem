package com.processing.taskmanagementsystem.dto.user;

import com.processing.taskmanagementsystem.dto.task.TaskResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

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