package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
import com.processing.taskmanagementsystem.dto.request.user.UserRequestDto;
import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.entity.UserRoles;

import java.time.LocalDateTime;
import java.util.List;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponseDto mapEntityToUserResponse(User user) {
        return UserResponseDto.builder()
                .uuid(user.getUuid())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .taskResponse(TaskMapper.mapEntityToResponseDto(user.getTask()))
                .build();
    }

    public static User mapRequestToEntity(UserRequestDto userRequestDto) {
        User user = new User();

        user.setUuid(userRequestDto.getUuid());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());

        return user;
    }

    public static User mapRegistrationRequestToEntity(Role roleByName, UserRegistrationRequest userRegistration) {
        User user = new User();

        user.setUuid(userRegistration.getUuid());
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setUsername(userRegistration.getUsername());
        user.setEnabled(true);
        user.setCreated(LocalDateTime.now());
        UserRoles userRoles = UserRoleMapper.mapRequestToEntity(roleByName, user);
        user.setUserRoles(List.of(userRoles));

        return user;
    }
}