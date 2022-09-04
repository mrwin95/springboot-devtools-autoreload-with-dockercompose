package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.User;
import com.example.userservice.repository.RoleRepo;
import com.example.userservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepo repo;

    private final RoleRepo roleRepo;
    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user = repo.findUserByUsername(username);
        Role rolesave = roleRepo.findRoleByName(role).get();
        user.getRoles().add(rolesave);

    }

    @Override
    public User findUserByUsername(String username) {
        return repo.findUserByUsername(username);
    }

    @Override
    public User getUser(String username) {
        return repo.findUserByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return repo.findAll();
    }
}
