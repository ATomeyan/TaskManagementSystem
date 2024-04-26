package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.request.comment.CommentRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.comment.CommentUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.comment.CommentResponseDto;
import com.processing.taskmanagementsystem.entity.Comment;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.mapper.CommentMapper;
import com.processing.taskmanagementsystem.repository.CommentRepository;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.CommentService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 25.04.2024
 */

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {

        User user = userRepository.findById(commentRequestDto.getUserUid()).orElseThrow(() ->
                new NotFoundException(String.format("User by uid not found: %s", commentRequestDto.getUserUid())));
        Task task = taskRepository.findById(commentRequestDto.getTaskUid()).orElseThrow(() ->
                new NotFoundException((String.format("Task by uid not found: %s", commentRequestDto.getTaskUid()))));

        Comment comment = CommentMapper.mapRequestDtoToComment(task, user, commentRequestDto);

        Comment saved = commentRepository.save(comment);
        LOGGER.info("Comment successfully saved.");

        return CommentMapper.mapCommentToResponseDto(saved);
    }

    @Override
    public CommentResponseDto getCommentById(String uuid) {

        validateUuid(uuid);

        Comment comment = commentRepository.findById(uuid).orElseThrow(() ->
                new NotFoundException(String.format("Comment by uid does not found: %s", uuid)));

        return CommentMapper.mapCommentToResponseDto(comment);
    }

    @Override
    public CommentResponseDto updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
        String uuid = commentUpdateRequestDto.getUuid();

        validateUuid(uuid);

        Comment comment = commentRepository.findById(uuid).orElseThrow(() ->
                new NotFoundException(String.format("Comment by uid does not found: %s", uuid)));

        comment.setContent(commentUpdateRequestDto.getContent());

        Comment updatedComment = commentRepository.save(comment);

        return CommentMapper.mapCommentToResponseDto(updatedComment);
    }

    @Override
    public void deleteComment(String uuid) {

        validateUuid(uuid);
        Optional<Comment> comment = commentRepository.findById(uuid);

        if (comment.isPresent()) {
            commentRepository.deleteById(uuid);
            LOGGER.info("Comment by {} uid was deleted.", uuid);
        } else {
            LOGGER.error("Comment by {} uid was not found.", uuid);
            throw new NotFoundException(String.format("Comment by uid was not found. %s", uuid));
        }
    }

    private void validateUuid(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            LOGGER.error("UUID {} is not valid.", uuid);
            throw new InvalidObjectException(String.format("UUID is not valid. %s", uuid));
        }
    }
}