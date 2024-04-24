package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Status;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.mapper.TaskMapper;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.service.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {

        validateTaskRequest(taskRequestDto);

        Task createdTask = TaskMapper.mapRequestDtoToEntity(taskRequestDto);
        taskRepository.save(createdTask);

        LOGGER.info("Task created.");

        return TaskMapper.mapEntityToResponseDto(createdTask);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTask(TaskUpdateRequestDto taskUpdateRequestDto) {

        validateUpdateRequest(taskUpdateRequestDto);

        String uuid = taskUpdateRequestDto.getUuid();
        Task taskById = validateTaskByUuid(uuid);

        Task task = TaskMapper.mapUpdateRequestToEntity(taskById, taskUpdateRequestDto);

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.mapEntityToResponseDto(updatedTask);
    }

    @Override
    public TaskResponseDto getTaskById(String uuid) {

        validateUuid(uuid);

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
    public List<TaskResponseDto> getAllTaskByStatus(String status, Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("status").ascending());

        Page<Task> tasksByCriteria = taskRepository.findAllTasksByStatus(Status.valueOf(status), pageable);

        if (tasksByCriteria.isEmpty()) {
            LOGGER.error("Task by status {} does not found.", status);
            throw new NotFoundException(String.format("Task by status does not found. %S", status));
        }

        return tasksByCriteria.stream().map(TaskMapper::mapEntityToResponseDto).toList();
    }

    @Override
    public boolean deleteTask(String uuid) {

        validateUuid(uuid);

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

    @Override
    public List<TaskResponseDto> getAllTasksByPriority(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("priority").ascending());

        Page<Task> allTasksSortedByPriority = taskRepository.findAll(pageable);

        if (allTasksSortedByPriority.isEmpty()) {
            LOGGER.error("Tasks does not found.");
            throw new NotFoundException("Tasks does not found.");
        }

        return allTasksSortedByPriority.stream().map(TaskMapper::mapEntityToResponseDto).toList();
    }

    @Override
    public TaskResponseDto updateTaskStatus(String uuid, String status) {
        validateUuid(uuid);

        Task task = validateTaskByUuid(uuid);

        task.setStatus(Status.valueOf(status));

        Task updatedStatus = taskRepository.save(task);

        return TaskMapper.mapEntityToResponseDto(updatedStatus);
    }

    private Task validateTaskByUuid(String uuid) {
        Optional<Task> byUuid = taskRepository.findById(uuid);
        if (byUuid.isEmpty()) {
            LOGGER.error("Task by {} uuid was not found.", uuid);
            throw new NotFoundException(String.format("Task by uuid was not found. %s", uuid));
        } else {
            return byUuid.get();
        }
    }

    private void validateTaskRequest(TaskRequestDto taskRequestDto) {
        if (taskRequestDto == null) {
            LOGGER.error("Task request is null.");
            throw new InvalidObjectException("Task request is null.");
        }
    }

    private void validateUpdateRequest(TaskUpdateRequestDto taskUpdateRequestDto) {
        if (taskUpdateRequestDto == null) {
            LOGGER.error("Update request object is invalid.");
            throw new InvalidObjectException("Update request object is invalid.");
        }
    }

    private void validateUuid(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            LOGGER.error("UUID {} is not valid.", uuid);
            throw new InvalidObjectException(String.format("UUID is not valid. %s", uuid));
        }
    }
}