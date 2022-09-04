package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepo repo;
    @Override
    public Optional<Role> findRoleByName(String name) {
        Optional<Role> role = repo.findRoleByName(name).stream().findFirst();
        return role;
    }
}
