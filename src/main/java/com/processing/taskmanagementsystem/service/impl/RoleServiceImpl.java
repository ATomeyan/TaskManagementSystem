package com.processing.taskmanagementsystem.service.impl;

import com.processing.taskmanagementsystem.entity.Role;
import com.processing.taskmanagementsystem.repository.RoleRepository;
import com.processing.taskmanagementsystem.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String role) {
        return roleRepository.findRoleByName(role);
    }
}