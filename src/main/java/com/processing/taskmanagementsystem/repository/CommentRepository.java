package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Artur Tomeyan
 * @date 25.04.2024
 */
public interface CommentRepository extends JpaRepository<Comment, String> {
}