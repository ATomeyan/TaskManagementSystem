package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.task.TaskUpdateStatusRequestDto;
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
    public ResponseEntity<List<TaskResponseDto>> getAllTasks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "title") String sortBy) {

        List<TaskResponseDto> allTasks = taskService.getAllTasks(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/by/{status}")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByStatus(@PathVariable String status,
                                                                     @RequestParam(defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(defaultValue = "status") String sortBy) {

        List<TaskResponseDto> allTaskByCriteria = taskService.getAllTaskByStatus(status, pageNo, pageSize, sortBy);

        return new ResponseEntity<>(allTaskByCriteria, HttpStatus.OK);
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

    @PutMapping
    public ResponseEntity<TaskResponseDto> updateTask(@RequestBody TaskUpdateRequestDto taskUpdateRequestDto) {
        TaskResponseDto taskResponseDto = taskService.updateTask(taskUpdateRequestDto);

        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Boolean> deleteTaskByUid(@PathVariable String uuid) {
        boolean deleteTask = taskService.deleteTask(uuid);

        return new ResponseEntity<>(deleteTask, HttpStatus.OK);
    }

    @GetMapping("/priority")
    public ResponseEntity<List<TaskResponseDto>> getTasksSortedByPriority(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                          @RequestParam(defaultValue = "10") Integer pageSize) {

        List<TaskResponseDto> allTasksSortedByPriority = taskService.getAllTasksByPriority(pageNo, pageSize);

        return new ResponseEntity<>(allTasksSortedByPriority, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<TaskResponseDto> updateTaskStatus(@RequestBody TaskUpdateStatusRequestDto taskUpdateStatusRequestDto) {
        TaskResponseDto taskResponseDto = taskService.updateTaskStatus(taskUpdateStatusRequestDto.getTaskId(), taskUpdateStatusRequestDto.getStatus());

        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }
}