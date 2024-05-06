package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.request.notification.NotificationRequestDto;
import com.processing.taskmanagementsystem.dto.response.notification.NotificationResponse;
import com.processing.taskmanagementsystem.entity.Notification;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.mapper.NotificationMapper;
import com.processing.taskmanagementsystem.repository.NotificationRepository;
import com.processing.taskmanagementsystem.repository.UserRepository;
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
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public NotificationResponse generateNotificationByAssignedNewTask(NotificationRequestDto notificationRequestDto) {

        if (notificationRequestDto == null) {
            LOGGER.error("Notification request is null.");
            throw new InvalidObjectException("Notification request is null.");
        }

        String userUid = notificationRequestDto.getUserUid();

        User user = userRepository.findById(userUid).orElseThrow(() ->
                new NotFoundException(String.format("User by uid not found: %s", userUid)));

        Notification notification = NotificationMapper.mapNotificationRequestToResponse(user, notificationRequestDto);

        Notification createdNotification = notificationRepository.save(notification);
        LOGGER.info("Notification successfully created.");

        return NotificationMapper.mapNotificationToResponse(createdNotification);
    }

    @Override
    public NotificationResponse generateNotificationByUpdatingTask(NotificationRequestDto notificationRequestDto) {
        return null;
    }

    @Override
    public void deleteNotification(String uuid) {

    }

    @Scheduled(cron = " * * * * * *")
    private void generateNotificationByDueDateDeadLine(NotificationRequestDto notificationRequestDto) {

    }
}