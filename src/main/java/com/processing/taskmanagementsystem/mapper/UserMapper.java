package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
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
                .username(user.getUsername())
                .build();
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

    public static User mapResponseToEntity(UserResponseDto userResponseDto) {
        User user = new User();

        user.setUuid(userResponseDto.getUuid());
        user.setFirstName(userResponseDto.getFirstName());
        user.setLastName(userResponseDto.getLastName());

        return user;
    }
}