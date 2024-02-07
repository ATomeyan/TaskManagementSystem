package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.task.TaskRequestDto;
import com.processing.taskmanagementsystem.entity.Task;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task mapRequestDtoToEntity(TaskRequestDto taskRequestDto){
        Task task = new Task();

        task.setUuid(taskRequestDto.getUuid());
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStartDate(taskRequestDto.getStartDate());
        task.setDueDate(taskRequestDto.getDueDate());
        task.setPriority(taskRequestDto.getPriority());
        task.setStatus(taskRequestDto.getStatus());

        return task;
    }
}