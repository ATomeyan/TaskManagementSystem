package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.comment.CommentRequestDto;
import com.processing.taskmanagementsystem.dto.response.comment.CommentResponseDto;
import com.processing.taskmanagementsystem.entity.*;

import java.util.List;

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

        TaskComment taskComment = TaskCommentMapper.mapRequestToEntity(comment, task);
        comment.setTaskComments(List.of(taskComment));
        task.setTaskComments(List.of(taskComment));

        UserComment userComment = UserCommentMapper.mapRequestToEntity(comment, user);
        comment.setUserComments(List.of(userComment));
        user.setUserComments(List.of(userComment));

        return comment;
    }

    public static CommentResponseDto mapCommentToResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .task(comment.getTaskComments().stream().iterator().next().getUuid())
                .content(comment.getContent())
                .author(comment.getUserComments().iterator().next().getUuid())
                .build();
    }
}
