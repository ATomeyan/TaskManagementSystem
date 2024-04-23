package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Priority;
import com.processing.taskmanagementsystem.entity.Status;
import com.processing.taskmanagementsystem.entity.Task;

import java.time.LocalDate;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task mapRequestDtoToEntity(TaskRequestDto taskRequestDto) {
        return createTaskEntity(
                taskRequestDto.getUuid(),
                taskRequestDto.getTitle(),
                taskRequestDto.getDescription(),
                taskRequestDto.getDueDate(),
                Priority.valueOf(taskRequestDto.getPriority()),
                Status.valueOf(taskRequestDto.getStatus()));
    }

    public static TaskResponseDto mapEntityToResponseDto(Task task) {
        return TaskResponseDto.builder()
                .uuid(task.getUuid())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .priority(task.getPriority().getPriority())
                .status(task.getStatus().getStatus())
                .build();
    }

    public static Task mapUpdateRequestToEntity(Task existingTask, TaskUpdateRequestDto taskUpdateRequestDto) {
        existingTask.setUuid(taskUpdateRequestDto.getUuid());
        existingTask.setTitle(taskUpdateRequestDto.getTitle());
        existingTask.setDescription(taskUpdateRequestDto.getDescription());
        existingTask.setDueDate(taskUpdateRequestDto.getDueDate());
        existingTask.setPriority(Priority.valueOf(taskUpdateRequestDto.getPriority()));
        existingTask.setStatus(Status.valueOf(taskUpdateRequestDto.getStatus()));

        return existingTask;
    }

    private static Task createTaskEntity(String uuid, String title, String description, LocalDate dueDate, Priority priority, Status status) {
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