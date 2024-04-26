package com.processing.taskmanagementsystem.dto.request.comment;

import jakarta.validation.constraints.NotBlank;
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
public class CommentRequestDto implements Serializable {

    private String uuid;
    @NotBlank
    private String content;
    @NotBlank
    private String userUid;
    @NotBlank
    private String taskUid;
}