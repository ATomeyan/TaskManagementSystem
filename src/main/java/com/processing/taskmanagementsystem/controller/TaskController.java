package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.task.TaskRequestDto;
import com.processing.taskmanagementsystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> createTask(TaskRequestDto taskRequestDto) {
        taskService.createTask(taskRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}