package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.dto.request.taskuser.TaskUserRequest;
import com.processing.taskmanagementsystem.entity.Task;
import com.processing.taskmanagementsystem.entity.TaskUser;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.exception.NotFoundException;
import com.processing.taskmanagementsystem.exception.UserAssignedToTaskException;
import com.processing.taskmanagementsystem.mapper.TaskUserMapper;
import com.processing.taskmanagementsystem.repository.TaskRepository;
import com.processing.taskmanagementsystem.repository.TaskUserRepository;
import com.processing.taskmanagementsystem.repository.UserRepository;
import com.processing.taskmanagementsystem.service.TaskUserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskUserServiceImpl implements TaskUserService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TaskUserService.class);
    private final TaskUserRepository taskUserRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskUserServiceImpl(TaskUserRepository taskUserRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.taskUserRepository = taskUserRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void assignTaskToUser(TaskUserRequest taskUserRequest){

        String userUid = taskUserRequest.getUserUid();
        String taskUid = taskUserRequest.getTaskUid();

        if (userUid == null || userUid.isBlank()) {
            LOGGER.error("User uid {} is invalid.", userUid);
            throw new InvalidObjectException(String.format("User uid {} is invalid. %s", userUid));
        }

        if (taskUid == null || taskUid.isBlank()) {
            LOGGER.error("Task uid {} is invalid.", taskUid);
            throw new InvalidObjectException(String.format("Task uid {} is invalid. %s", taskUid));
        }

        Optional<User> user = userRepository.findById(userUid);

        ensureUserExists(user);

        boolean hasTask = user.get().getTaskUsers().stream().iterator().hasNext();
        ensureUserHasNoTasks(hasTask, user);

        Optional<Task> task = taskRepository.findById(taskUid);

        ensureTaskExists(task);

        TaskUser taskUser = TaskUserMapper.mapRequestToEntity(task.get(), user.get());
        taskUserRepository.save(taskUser);

        LOGGER.info("Task successfully assigned to user.");
    }

    private void ensureUserHasNoTasks(boolean hasTask, Optional<User> user) {
        if (hasTask) {
            LOGGER.error("The task already assigned to this user.");
            throw new UserAssignedToTaskException(String.format("The task already assigned to this user %s:", user));
        }
    }

    private void ensureUserExists(Optional<User> user) {
        if (user.isEmpty()) {
            LOGGER.error("The user by {} uid was not found.", user);
            throw new NotFoundException(String.format("The user by uid was not found %s:", user));
        }
    }

    private void ensureTaskExists(Optional<Task> task) {
        if (task.isEmpty()) {
            LOGGER.error("The task by {} uid was not found.", task);
            throw new NotFoundException(String.format("The task by uid was not found %s:", task));
        }
    }
}