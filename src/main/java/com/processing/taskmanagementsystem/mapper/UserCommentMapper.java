package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.entity.Comment;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.entity.UserComment;

import java.util.List;

public class UserCommentMapper {

    private UserCommentMapper() {
    }

    public static void mapRequestToEntity(Comment comment, User user) {
        UserComment userComment = new UserComment();

        userComment.setComment(comment);
        userComment.setUser(user);

        comment.setUserComments(List.of(userComment));
        user.setUserComments(List.of(userComment));
    }
}