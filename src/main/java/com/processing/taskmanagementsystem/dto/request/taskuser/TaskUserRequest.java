package com.processing.taskmanagementsystem.dto.request.taskuser;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class TaskUserRequest implements Serializable {
    @NotBlank
    private String userUid;
    @NotBlank
    private String taskUid;
}