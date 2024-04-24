package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(TaskUpdateRequestDto taskUpdateRequestDto);

    TaskResponseDto getTaskById(String uuid);

    List<TaskResponseDto> getAllTasks(Integer pageNo, Integer pageSize, String sortBy);

    List<TaskResponseDto> getAllTaskByStatus(String status, Integer pageNo, Integer pageSize, String sortBy);

    boolean deleteTask(String uuid);

    List<TaskResponseDto> getAllTasksByPriority(Integer pageNo, Integer pageSize);

    TaskResponseDto updateTaskStatus(String uuid, String status);
}