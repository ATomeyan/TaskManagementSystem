package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.entity.*;

public class TaskUserMapper {

    private TaskUserMapper() {
    }

    public static TaskUser mapRequestToEntity(Task task, User user) {
        TaskUser taskUser = new TaskUser();

        taskUser.setTask(task);
        taskUser.setUser(user);

        return taskUser;
    }
}