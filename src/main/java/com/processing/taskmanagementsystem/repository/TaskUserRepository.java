package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUser, String> {
}