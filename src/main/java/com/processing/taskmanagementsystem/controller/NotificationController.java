package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String uid) {
        notificationService.deleteNotification(uid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}