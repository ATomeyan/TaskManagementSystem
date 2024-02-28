package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.AssignedException;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.mapper.TaskMapper;
import com.processing.taskmanagementsystem.mapper.UserMapper;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.service.TaskService;
import com.processing.taskmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        String username = taskRequestDto.getUser().getUsername();
        UserResponseDto byUsername = userService.findByUsername(username);

        if (byUsername == null) {
            LOGGER.error("The user by username was not found.");
            throw new NotFoundException(String.format("The user by username was not found %s:", username));
        }

        if (byUsername.getTaskResponse() != null) {
            LOGGER.error("The task already assigned to this user.");
            throw new AssignedException(String.format("The task already assigned to this username %s:", username));
        }

        User user = UserMapper.mapResponseToEntity(byUsername);
        Task createdTask = TaskMapper.mapRequestDtoToEntity(user, taskRequestDto);
        taskRepository.save(createdTask);

        LOGGER.info("Task created.");

        return TaskMapper.mapEntityToResponseDto(createdTask);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTask(TaskUpdateRequestDto taskUpdateRequestDto) {

        if (taskUpdateRequestDto == null) {
            LOGGER.error("Update request object is invalid.");
            throw new InvalidObjectException("Update request object is invalid.");
        }

//        TaskResponseDto taskById = getTaskById(taskUpdateRequestDto.getUuid());

        Task task = TaskMapper.mapUpdateRequestToEntity(taskUpdateRequestDto);

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.mapEntityToResponseDto(updatedTask);
    }

    @Override
    public TaskResponseDto getTaskById(String uuid) {

        if (uuid == null || uuid.isBlank()) {
            LOGGER.error("UUID {} is not valid.", uuid);
            throw new InvalidObjectException(String.format("UUID is not valid. %s", uuid));
        }

        Optional<Task> taskById = taskRepository.findById(uuid);

        if (taskById.isEmpty()) {
            LOGGER.error("User by {} uid was not found.", uuid);
            throw new InvalidObjectException(String.format("User by uid was not found. %s", uuid));
        }

        return TaskMapper.mapEntityToResponseDto(taskById.get());
    }

    @Override
    public List<TaskResponseDto> getAllTasks(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        Page<Task> allTasks = taskRepository.findAll(pageable);

        if (allTasks.isEmpty()) {
            LOGGER.error("Task does not found.");
            throw new NotFoundException("Task does not found.");
        }

        return allTasks.stream().map(TaskMapper::mapEntityToResponseDto).toList();
    }

    @Override
    public List<TaskResponseDto> getAllTaskByCriteria(String criteria, Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        Page<Task> tasksByCriteria = taskRepository.findTasksByCriteria(criteria, pageable);

        if (tasksByCriteria.isEmpty()) {
            LOGGER.error("Task does not found.");
            throw new NotFoundException("Task does not found.");
        }

        return tasksByCriteria.stream().map(TaskMapper::mapEntityToResponseDto).toList();
    }

    @Override
    public boolean deleteTask(String uuid) {

        if (uuid == null || uuid.isBlank()) {
            LOGGER.error("UUID {} is not valid.", uuid);
            throw new InvalidObjectException(String.format("UUID is not valid. %s", uuid));
        }

        Optional<Task> byId = taskRepository.findById(uuid);

        if (byId.isPresent()) {
            taskRepository.deleteById(uuid);
            LOGGER.info("Task by {} uid was deleted.", uuid);
            return true;
        } else {
            LOGGER.error("Task by {} uid was not found.", uuid);
            throw new NotFoundException(String.format("Task by uid was not found. %s", uuid));
        }
    }
}