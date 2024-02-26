package com.processing.taskmanagementsystem.dto.request.task;

import com.processing.taskmanagementsystem.dto.request.user.UserRequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TaskRequestDto implements Serializable {

    private String uuid;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private LocalDate startDate;
    @NotBlank
    private LocalDate dueDate;
    @NotBlank
    private String priority;
    @NotBlank
    private String status;
    private UserRequestDto userRequestDto;
}