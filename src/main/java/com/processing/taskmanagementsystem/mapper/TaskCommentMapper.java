package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.entity.Comment;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.TaskComment;

import java.util.List;

public class TaskCommentMapper {

    private TaskCommentMapper() {
    }

    public static void mapRequestToEntity(Comment comment, Task task) {
        TaskComment taskComment = new TaskComment();

        taskComment.setComment(comment);
        taskComment.setTask(task);

        comment.setTaskComments(List.of(taskComment));
        task.setTaskComments(List.of(taskComment));
    }
}