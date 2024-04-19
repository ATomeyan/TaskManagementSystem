package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.TaskUser;
import com.processing.taskmanagementsystem.entity.User;

import java.util.List;

public class TaskUserMapper {

    private TaskUserMapper() {
    }

    public static TaskUser mapRequestToEntity(Task task, User user) {
        TaskUser taskUser = new TaskUser();

        taskUser.setTask(task);
        taskUser.setUser(user);

        task.setTaskUsers(List.of(taskUser));
        user.setTaskUsers(List.of(taskUser));

        return taskUser;
    }
}