package com.processing.taskmanagementsystem.dto.task;

import com.processing.taskmanagementsystem.dto.user.UserResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TaskResponseDto implements Serializable {

    private String uuid;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private UserResponseDto userResponseDto;
}