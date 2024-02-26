package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Task;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task mapRequestDtoToEntity(TaskRequestDto taskRequestDto) {
        Task task = new Task();

        task.setUuid(taskRequestDto.getUuid());
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setDueDate(taskRequestDto.getDueDate());
        task.setPriority(taskRequestDto.getPriority());
        task.setStatus(taskRequestDto.getStatus());
        task.setUser(UserMapper.mapRequestToEntity(taskRequestDto.getUserRequestDto()));

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
                .userResponseDto(UserMapper.mapEntityToUserResponse(task.getUser()))
                .build();
    }
}