package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.notification.NotificationRequestDto;
import com.processing.taskmanagementsystem.dto.response.notification.NotificationResponse;
import com.processing.taskmanagementsystem.entity.Notification;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.entity.UserNotification;

import java.util.List;

public class NotificationMapper {

    private NotificationMapper() {
    }

    public static Notification mapNotificationRequestToResponse(User user, NotificationRequestDto notificationRequestDto) {

        Notification notification = new Notification();

        notification.setDateTime(notificationRequestDto.getDateTime());
        notification.setMessage(notificationRequestDto.getMessage());
        notification.setIsRead(notificationRequestDto.isRead());

        UserNotification userNotification = new UserNotification();
        userNotification.setUser(user);
        userNotification.setNotification(notification);

        notification.setUser(List.of(userNotification));

        return notification;
    }

    public static NotificationResponse mapNotificationToResponse(Notification notification) {

        return NotificationResponse.builder()
                .dateTime(notification.getDateTime())
                .message(notification.getMessage())
                .build();
    }
}