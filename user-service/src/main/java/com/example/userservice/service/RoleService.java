package com.example.userservice.service;

import com.example.userservice.domain.Role;

import java.util.Optional;

public interface RoleService {
    public Optional<Role> findRoleByName(String name);

}
