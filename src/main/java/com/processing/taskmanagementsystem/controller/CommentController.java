package com.processing.taskmanagementsystem.controller;

import com.processing.taskmanagementsystem.dto.request.comment.CommentRequestDto;
import com.processing.taskmanagementsystem.dto.request.update.comment.CommentUpdateRequestDto;
import com.processing.taskmanagementsystem.dto.response.comment.CommentResponseDto;
import com.processing.taskmanagementsystem.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> addComment(@RequestBody @Valid CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.addComment(commentRequestDto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/by/{uuid}")
    public ResponseEntity<CommentResponseDto> getCommentByUid(@PathVariable String uuid) {
        CommentResponseDto commentById = commentService.getCommentById(uuid);

        return new ResponseEntity<>(commentById, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentUpdateRequestDto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteComment(@PathVariable String uuid) {
        commentService.deleteComment(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}