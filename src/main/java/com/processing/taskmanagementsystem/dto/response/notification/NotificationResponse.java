package com.processing.taskmanagementsystem.dto.response.notification;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class NotificationResponse implements Serializable {

    private LocalDateTime dateTime;
    private String message;
}