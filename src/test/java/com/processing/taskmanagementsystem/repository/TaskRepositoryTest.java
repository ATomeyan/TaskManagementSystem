package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void init() {
        Task task1 = Task.builder()
                .title("Button")
                .description("Create btn.")
                .dueDate(LocalDate.now())
                .priority("1")
                .status("To do")
                .build();

        Task task2 = Task.builder()
                .title("Pop up menu")
                .description("Create pop up menu.")
                .dueDate(LocalDate.now())
                .priority("1")
                .status("To do")
                .build();

        Task task3 = Task.builder()
                .title("Pop up menu")
                .description("Create pop up menu.")
                .dueDate(LocalDate.now())
                .priority("1")
                .status("To do")
                .build();

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
    }

    @Test
    public void addTask() {

    }
}