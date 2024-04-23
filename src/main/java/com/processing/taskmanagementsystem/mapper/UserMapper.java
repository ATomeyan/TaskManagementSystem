package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.request.authentication.UserRegistrationRequest;
import com.processing.taskmanagementsystem.dto.request.update.authentication.UserUpdateRequest;
import com.processing.taskmanagementsystem.dto.response.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.entity.UserRoles;

import java.util.Collections;

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

    public static User mapRegistrationRequestToEntity(Role role, UserRegistrationRequest userRegistration) {
        User user = new User();

        user.setUuid(userRegistration.getUuid());
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setUsername(userRegistration.getUsername());
        user.setEnabled(true);

        UserRoles userRoles = UserRoleMapper.mapRequestToEntity(role, user);
        user.setUserRoles(Collections.singletonList(userRoles));

        return user;
    }

    public static User mapUpdateRequestToEntity(User user, UserUpdateRequest userUpdateRequest) {
        user.setUuid(userUpdateRequest.getUuid());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setUsername(userUpdateRequest.getUsername());

        return user;
    }
}
