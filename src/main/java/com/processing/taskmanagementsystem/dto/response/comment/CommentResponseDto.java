package com.processing.taskmanagementsystem.dto.response.comment;

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
public class CommentResponseDto implements Serializable {

    private String content;
    private String author;
    private String task;
}