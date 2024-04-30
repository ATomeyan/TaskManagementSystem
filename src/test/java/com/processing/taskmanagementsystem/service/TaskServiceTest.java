package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.task.TaskRequestDto;
import com.processing.taskmanagementsystem.dto.response.task.TaskResponseDto;
import com.processing.taskmanagementsystem.entity.Priority;
import com.processing.taskmanagementsystem.entity.Status;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskService;
    private Task task;
    private TaskRequestDto taskRequest;

    @BeforeEach
    public void init() {
        task = Task.builder()
                .title("Button")
                .description("Create btn.")
                .dueDate(LocalDate.now())
                .priority(Priority.valueOf("Normal"))
                .status(Status.valueOf("Scheduled"))
                .build();

        taskRequest = TaskRequestDto.builder()
                .title("Button")
                .description("Create btn.")
                .dueDate(LocalDate.now())
                .priority("Normal")
                .status("Scheduled")
                .build();
    }

    @Test
    void givenTask_whenSaveTask_thenReturnSavedTask() {

        given(taskRepository.findById(task.getUuid())).willReturn(Optional.empty());

        given(taskRepository.save(task)).willReturn(task);

        TaskResponseDto savedTask = taskService.createTask(taskRequest);

        System.out.println(savedTask);
        assertThat(savedTask).isNotNull();
    }
}