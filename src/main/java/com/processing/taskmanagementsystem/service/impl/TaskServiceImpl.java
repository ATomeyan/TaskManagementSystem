package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.task.TaskResponseDto;
import com.processing.taskmanagementsystem.dto.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.exception.AssignedException;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.mapper.TaskMapper;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.service.TaskService;
import com.processing.taskmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {

        if (taskRequestDto == null) {
            LOGGER.error("Task request is null.");
            throw new InvalidObjectException("Task request is null.");
        }

        String username = taskRequestDto.getUserRequestDto().getUsername();
        UserResponseDto byUsername = userService.findByUsername(username);

        if (byUsername == null) {
            LOGGER.error("The user by username was not found.");
            throw new NotFoundException(String.format("The user by username was not found %s:", username));
        }

        if (byUsername.getTaskResponse() != null) {
            LOGGER.error("The task already assigned to this user.");
            throw new AssignedException(String.format("The task already assigned to this username %s:", username));
        }

        Task createdTask = TaskMapper.mapRequestDtoToEntity(taskRequestDto);
        taskRepository.save(createdTask);

        LOGGER.info("Task created.");

        return TaskMapper.mapEntityToResponseDto(createdTask);
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