package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    @Query("select t from Task t where t.status=:criteria")
    Page<Task> findTasksByCriteria(String criteria, Pageable pageable);
}