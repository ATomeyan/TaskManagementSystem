package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void init() {
        task = Task.builder()
                .title("Button")
                .description("Create btn.")
                .dueDate(LocalDate.now())
                .priority("1")
                .status("To do")
                .build();

        Task task1 = Task.builder()
                .title("Pop up")
                .description("Create pop up menu.")
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

        taskRepository.save(task);
        taskRepository.save(task1);
        taskRepository.save(task2);
    }

    @Test
    void givenTask_whenSaveTask_thenReturnSavedTask() {

        Task savedTask = taskRepository.save(this.task);

        assertThat(savedTask).isNotNull();
    }

    @Test
    void givenTaskList_whenFindAll_thenReturnTaskList() {

        List<Task> all = taskRepository.findAll();

        assertThat(all).isNotNull();
        assertThat(all).hasSizeGreaterThan(2);
    }

    @Test
    void givenTaskUid_whenFind_thenReturnTask() {

        Optional<Task> byId = taskRepository.findById(task.getUuid());

        assertThat(byId).isPresent();
    }

    @Test
    void givenTaskCriteria_whenFind_thenReturnTask() {

        Page<Task> button = taskRepository.findTasksByCriteria("Button", PageRequest.of(0, 1));

        assertThat(button).isNotNull();
    }

    @Test
    void givenTask_whenDelete_thenRemoveTask() {

        taskRepository.deleteById(task.getUuid());

        Optional<Task> byId = taskRepository.findById(task.getUuid());

        assertThat(byId).isEmpty();
    }
}