package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.request.notification.NotificationRequestDto;
import com.processing.taskmanagementsystem.repository.NotificationRepository;
import com.processing.taskmanagementsystem.service.NotificationService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Artur Tomeyan
 * @date 30.04.2024
 */

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void generateNotificationByAssignedNewTask(NotificationRequestDto notificationRequestDto) {

    }

    @Override
    public void generateNotificationByUpdatingTask(NotificationRequestDto notificationRequestDto) {

    }

    @Override
    public void deleteNotification(String uuid) {

    }

    @Scheduled(cron = " * * * * * *")
    private void generateNotificationByDueDateDeadLine(NotificationRequestDto notificationRequestDto) {

    }
}