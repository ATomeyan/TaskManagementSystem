package com.processing.taskmanagementsystem.service.impl;

import com.processing.taskmanagementsystem.dto.request.comment.CommentRequestDto;
import com.processing.taskmanagementsystem.dto.response.comment.CommentResponseDto;
import com.processing.taskmanagementsystem.entity.Comment;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.repository.CommentRepository;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CommentServiceImplTest {

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TaskRepository taskRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void addComment_Success() {
        String userUid = "7df5442a-8fb9-4bb4-93c6-c0451d66a57e";
        String taskUid = "62d41f42-e381-45b6-a3c1-26e6a327a111";
        String content = "Test comment";

        User user = User.builder().uuid(userUid).build();
        Task task = Task.builder().uuid(taskUid).build();
        Comment savedComment = Comment.builder().content(content).build();

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .userUid(userUid)
                .taskUid(taskUid)
                .content(content)
                .build();

        when(userRepository.findById(userUid)).thenReturn(Optional.of(user));
        when(taskRepository.findById(taskUid)).thenReturn(Optional.of(task));
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);

        CommentResponseDto responseDto = commentService.addComment(commentRequestDto);

        verify(userRepository).findById(userUid);
        verify(taskRepository).findById(taskUid);
        verify(commentRepository).save(any(Comment.class));
        assertEquals(content, responseDto.getContent());
    }

    @Test
    void addComment_UserNotFound() {
        String userUid = "nonexistent-uid";
        String taskUid = "62d41f42-e381-45b6-a3c1-26e6a327a111";
        String content = "Test comment";

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .userUid(userUid)
                .taskUid(taskUid)
                .content(content)
                .build();

        when(userRepository.findById(userUid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commentService.addComment(commentRequestDto));

        verify(userRepository).findById(userUid);
        Mockito.verifyNoInteractions(taskRepository);
        Mockito.verifyNoInteractions(commentRepository);
    }

    @Test
    void addComment_TaskNotFound() {
        String userUid = "7df5442a-8fb9-4bb4-93c6-c0451d66a57e";
        String taskUid = "nonexistent-task-uid";
        String content = "Test comment";

        User user = User.builder().uuid(userUid).build();

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .userUid(userUid)
                .taskUid(taskUid)
                .content(content)
                .build();

        when(userRepository.findById(userUid)).thenReturn(Optional.of(user));
        when(taskRepository.findById(taskUid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commentService.addComment(commentRequestDto));

        verify(userRepository).findById(userUid);
        verify(taskRepository).findById(taskUid);
        Mockito.verifyNoInteractions(commentRepository);
    }
}