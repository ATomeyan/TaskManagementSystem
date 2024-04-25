package com.processing.taskmanagementsystem.dto.request.update.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Artur Tomeyan
 * @date 25.04.2024
 */

@Setter
@Getter
@Builder
public class CommentUpdateRequestDto implements Serializable {

    private String uuid;
    private String content;
    private String userUid;
    private String taskUid;
}