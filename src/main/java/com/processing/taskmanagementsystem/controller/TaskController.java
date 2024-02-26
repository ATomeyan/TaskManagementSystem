package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> allTasks = taskService.getAllTasks();

        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<TaskResponseDto> getTaskByUid(@PathVariable String uuid) {
        TaskResponseDto taskById = taskService.getTaskById(uuid);

        return new ResponseEntity<>(taskById, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto task = taskService.createTask(taskRequestDto);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Boolean> deleteTaskByUid(@PathVariable String uuid) {
        boolean deleteTask = taskService.deleteTask(uuid);

        return new ResponseEntity<>(deleteTask, HttpStatus.OK);
    }
}