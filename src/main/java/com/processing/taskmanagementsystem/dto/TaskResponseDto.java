package com.processing.taskmanagementsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class TaskResponseDto implements Serializable {
    private UUID uuid;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;
}