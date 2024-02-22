package com.processing.taskmanagementsystem.mapper;

import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.entity.User;
import com.processing.taskmanagementsystem.entity.UserRoles;

public class UserRoleMapper {

    private UserRoleMapper() {
    }

    public static UserRoles mapRequestToEntity(Role role, User user) {
        UserRoles userRoles = new UserRoles();

        userRoles.setRole(role);
        userRoles.setUser(user);

        return userRoles;
    }
}