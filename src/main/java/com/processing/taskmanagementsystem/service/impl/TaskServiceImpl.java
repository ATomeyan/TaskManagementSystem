package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.TaskResponseDto;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        return null;
    }

    @Override
    public TaskResponseDto updateTask(TaskRequestDto taskRequestDto) {
        return null;
    }

    @Override
    public TaskResponseDto getTaskById(UUID uuid) {
        return null;
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return null;
    }

    @Override
    public void deleteTask(UUID uuid) {

    }
}