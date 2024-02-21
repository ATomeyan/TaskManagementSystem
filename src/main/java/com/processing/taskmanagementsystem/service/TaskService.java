package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.task.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto updateTask(TaskRequestDto taskRequestDto);
    TaskResponseDto getTaskById(UUID uuid);
    List<TaskResponseDto> getAllTasks();
    void deleteTask(UUID uuid);
}