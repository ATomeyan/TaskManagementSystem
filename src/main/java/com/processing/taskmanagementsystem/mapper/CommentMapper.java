package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.comment.CommentRequestDto;
import com.processing.taskmanagementsystem.dto.response.comment.CommentResponseDto;
import com.processing.taskmanagementsystem.entity.*;

/**
 * @author Artur Tomeyan
 * @date 25.04.2024
 */
public class CommentMapper {
    private CommentMapper() {
    }

    public static Comment mapRequestDtoToComment(Task task, User user, CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();

        comment.setContent(commentRequestDto.getContent());

        TaskCommentMapper.mapRequestToEntity(comment, task);

        UserCommentMapper.mapRequestToEntity(comment, user);

        return comment;
    }

    public static CommentResponseDto mapCommentToResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .task(comment.getTaskComments().stream().iterator().next().getTask().getUuid())
                .content(comment.getContent())
                .author(comment.getUserComments().iterator().next().getUser().getUsername())
                .build();
    }
}
