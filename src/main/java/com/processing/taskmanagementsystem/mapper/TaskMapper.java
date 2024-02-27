package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.TaskUser;
import com.processing.taskmanagementsystem.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task mapRequestDtoToEntity(User user, TaskRequestDto taskRequestDto) {
        Task task = new Task();

        task.setUuid(taskRequestDto.getUuid());
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setDueDate(taskRequestDto.getDueDate());
        task.setPriority(taskRequestDto.getPriority());
        task.setStatus(taskRequestDto.getStatus());
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
}