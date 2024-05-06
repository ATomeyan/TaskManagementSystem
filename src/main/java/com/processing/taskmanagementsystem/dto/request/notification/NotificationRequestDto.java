package com.processing.taskmanagementsystem.dto.request.notification;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Artur Tomeyan
 * @date 01.05.2024
 */

@Setter
@Getter
@Builder
public class NotificationRequestDto implements Serializable {

    @NotBlank
    private LocalDateTime dateTime;
    @NotBlank
    private String message;
    @NotBlank
    private boolean isRead;
    @NotBlank
    private String userUid;
}