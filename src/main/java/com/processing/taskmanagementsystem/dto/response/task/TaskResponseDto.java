package com.processing.taskmanagementsystem.dto.response.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponseDto implements Serializable {

    private String uuid;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private UserResponseDto userResponseDto;
}