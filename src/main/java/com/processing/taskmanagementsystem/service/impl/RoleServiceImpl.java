package com.processing.taskmanagementsystem.service.impl;

import ch.qos.logback.classic.Logger;
import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.exception.EmptyException;
import com.processing.taskmanagementsystem.exception.InvalidObjectException;
import com.processing.taskmanagementsystem.repository.RoleRepository;
import com.processing.taskmanagementsystem.service.RoleService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String roleName) {
        if (roleName.isBlank()) {
            LOGGER.error("Role {} is invalid.", roleName);
            throw new InvalidObjectException(String.format("Role is invalid. %s", roleName));
        }

        Role role = roleRepository.findRoleByName(roleName);

        if (role == null) {
            LOGGER.error("Role object {} are empty.", role);
            throw new EmptyException(String.format("Role object are empty. %s", role));
        }

        return role;
    }
}