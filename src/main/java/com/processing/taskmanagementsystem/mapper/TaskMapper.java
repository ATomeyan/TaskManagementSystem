package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.TaskUser;
import com.processing.taskmanagementsystem.entity.User;

import java.time.LocalDate;
import java.util.Collections;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task mapRequestDtoToEntity(User user, TaskRequestDto taskRequestDto) {
        Task task = createTaskEntity(
                taskRequestDto.getUuid(),
                taskRequestDto.getTitle(),
                taskRequestDto.getDescription(),
                taskRequestDto.getDueDate(),
                taskRequestDto.getPriority(),
                taskRequestDto.getStatus());

        TaskUser taskUser = TaskUserMapper.mapRequestToEntity(task, user);
        task.setTaskUsers(Collections.singletonList(taskUser));

        return task;
    }

    public static TaskResponseDto mapEntityToResponseDto(Task task) {
        TaskUser taskUser = task.getTaskUsers().stream().findFirst().orElseThrow();
        return TaskResponseDto.builder()
                .uuid(task.getUuid())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .status(task.getStatus())
                .userResponseDto(UserMapper.mapEntityToUserResponse(taskUser.getUser()))
                .build();
    }

    public static Task mapUpdateRequestToEntity(Task existingTask, TaskUpdateRequestDto taskUpdateRequestDto) {
        existingTask.setUuid(taskUpdateRequestDto.getUuid());
        existingTask.setTitle(taskUpdateRequestDto.getTitle());
        existingTask.setDescription(taskUpdateRequestDto.getDescription());
        existingTask.setDueDate(taskUpdateRequestDto.getDueDate());
        existingTask.setPriority(taskUpdateRequestDto.getPriority());
        existingTask.setStatus(taskUpdateRequestDto.getStatus());

        return existingTask;
    }

    private static Task createTaskEntity(String uuid, String title, String description, LocalDate dueDate, String priority, String status) {
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