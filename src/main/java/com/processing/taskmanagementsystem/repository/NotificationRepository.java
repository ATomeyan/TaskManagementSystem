package com.processing.taskmanagementsystem.repository;

import com.processing.taskmanagementsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artur Tomeyan
 * @date 30.04.2024
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {
}