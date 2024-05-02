package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.notification.NotificationRequestDto;

/**
 * @author Artur Tomeyan
 * @date 30.04.2024
 */
public interface NotificationService {

    void generateNotificationByAssignedNewTask(NotificationRequestDto notificationRequestDto);

    void generateNotificationByUpdatingTask(NotificationRequestDto notificationRequestDto);

    void deleteNotification(String uuid);
}