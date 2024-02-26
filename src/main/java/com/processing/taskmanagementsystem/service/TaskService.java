package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(TaskRequestDto taskRequestDto);

    TaskResponseDto getTaskById(String uuid);

    List<TaskResponseDto> getAllTasks();

    boolean deleteTask(String uuid);
}