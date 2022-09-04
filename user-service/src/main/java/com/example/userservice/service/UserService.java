package com.example.userservice.service;

import com.example.userservice.domain.Role;
import com.example.userservice.domain.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public Role saveRole(Role role);

    void addRoleToUser(String username, String role);
    public User findUserByUsername(String username);

    public User getUser(String username);

    List<User> getUsers();
}
