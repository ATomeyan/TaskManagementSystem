package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.request.taskuser.TaskUserRequest;
import com.processing.taskmanagementsystem.service.TaskUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/task-user")
public class TaskUserController {

    private final TaskUserService taskUserService;

    public TaskUserController(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

    @PostMapping
    public ResponseEntity<Void> assignTaskToUser(@RequestBody @Valid TaskUserRequest taskUserRequest) {

        taskUserService.assignTaskToUser(taskUserRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}