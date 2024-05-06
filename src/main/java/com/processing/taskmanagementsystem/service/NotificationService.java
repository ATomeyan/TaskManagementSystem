package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.notification.NotificationRequestDto;
import com.processing.taskmanagementsystem.dto.response.notification.NotificationResponse;

/**
 * @author Artur Tomeyan
 * @date 30.04.2024
 */
public interface NotificationService {

    NotificationResponse generateNotificationByAssignedNewTask(NotificationRequestDto notificationRequestDto);

    NotificationResponse generateNotificationByUpdatingTask(NotificationRequestDto notificationRequestDto);

    void deleteNotification(String uuid);
}