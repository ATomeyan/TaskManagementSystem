package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.comment.CommentRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.comment.CommentUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.comment.CommentResponseDto;

/**
 * @author Artur Tomeyan
 * @date 25.04.2024
 */
public interface CommentService {

    CommentResponseDto addComment(CommentRequestDto commentRequestDto);

    CommentResponseDto getCommentById(String uuid);

    CommentResponseDto updateComment(CommentUpdateRequestDto commentUpdateRequestDto);

    void deleteComment(String uuid);
}