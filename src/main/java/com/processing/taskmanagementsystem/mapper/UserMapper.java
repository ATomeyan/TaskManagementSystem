package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.dto.authentication.UserRegistration;
import com.processing.taskmanagementsystem.dto.user.UserRequestDto;
import com.processing.taskmanagementsystem.dto.user.UserResponseDto;
import com.processing.taskmanagementsystem.entity.User;

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

    public static User mapRegistrationRequestToEntity(UserRegistration userRegistration) {
        User user = new User();

        user.setUuid(userRegistration.getUuid());
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setUsername(userRegistration.getUsername());


        return user;
    }
}