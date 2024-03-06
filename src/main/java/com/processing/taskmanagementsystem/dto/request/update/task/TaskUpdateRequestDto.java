package com.processing.taskmanagementsystem.dto.request.update.task;

import com.processing.taskmanagementsystem.dto.request.update.user.UserUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TaskUpdateRequestDto implements Serializable {

    private String uuid;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private UserUpdateRequestDto user;
}