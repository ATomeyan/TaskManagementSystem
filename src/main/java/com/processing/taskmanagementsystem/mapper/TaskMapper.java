package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.TaskUser;
import com.processing.taskmanagementsystem.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task mapRequestDtoToEntity(User user, TaskRequestDto taskRequestDto) {

        Task task = setTaskEntity(taskRequestDto.getUuid(),
                taskRequestDto.getTitle(),
                taskRequestDto.getDescription(),
                taskRequestDto.getDueDate(),
                taskRequestDto.getPriority(),
                taskRequestDto.getStatus());

        task.setCreated(LocalDateTime.now());

        TaskUser taskUser = TaskUserMapper.mapRequestToEntity(task, user);
        task.setTaskUsers(List.of(taskUser));

        return task;
    }

    public static TaskResponseDto mapEntityToResponseDto(Task task) {

        return TaskResponseDto.builder()
                .uuid(task.getUuid())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .status(task.getStatus())
                .userResponseDto(UserMapper.mapEntityToUserResponse(task.getTaskUsers().stream().iterator().next().getUser()))
                .build();
    }

    public static Task mapUpdateRequestToEntity(Task existingTask, TaskUpdateRequestDto taskUpdateRequestDto) {
        existingTask.setUuid(taskUpdateRequestDto.getUuid());
        existingTask.setTitle(taskUpdateRequestDto.getTitle());
        existingTask.setDescription(taskUpdateRequestDto.getDescription());
        existingTask.setDueDate(taskUpdateRequestDto.getDueDate());
        existingTask.setPriority(taskUpdateRequestDto.getPriority());
        existingTask.setStatus(taskUpdateRequestDto.getStatus());

        existingTask.setUpdated(LocalDateTime.now());

        return existingTask;
    }

    private static Task setTaskEntity(String uuid, String title, String description, LocalDate dueDate, String priority, String status) {
        Task task = new Task();

        task.setUuid(uuid);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setPriority(priority);
        task.setStatus(status);

        return task;
    }
}