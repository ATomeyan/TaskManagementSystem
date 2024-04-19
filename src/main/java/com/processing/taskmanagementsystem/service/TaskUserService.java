package com.processing.taskmanagementsystem.service;

import com.processing.taskmanagementsystem.dto.request.taskuser.TaskUserRequest;

public interface TaskUserService {

    void assignTaskToUser(TaskUserRequest taskUserRequest);
}