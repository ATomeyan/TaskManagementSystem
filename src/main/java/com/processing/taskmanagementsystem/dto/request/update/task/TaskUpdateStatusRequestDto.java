package com.processing.taskmanagementsystem.dto.request.update.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Artur Tomeyan
 * @date 24.04.2024
 */

@Getter
@Setter
@Builder
public class TaskUpdateStatusRequestDto implements Serializable {

    String taskId;
    String status;
}