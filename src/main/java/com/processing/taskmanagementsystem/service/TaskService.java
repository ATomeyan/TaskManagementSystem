package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(TaskRequestDto taskRequestDto);

    TaskResponseDto getTaskById(String uuid);

    List<TaskResponseDto> getAllTasks();

    boolean deleteTask(String uuid);
}