package com.processing.taskmanagementsystem.dto.task;

import com.processing.taskmanagementsystem.dto.user.UserRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TaskRequestDto implements Serializable {

    private UUID uuid;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private String priority;
    @NotNull
    private String status;
    private UserRequestDto userRequestDto;
}