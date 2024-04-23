package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.Status;
import com.processing.taskmanagementsystem.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    Page<Task> findAllTasksByStatus(Status status, Pageable pageable);
}