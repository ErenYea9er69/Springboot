package com.example.pc.service;

import com.example.pc.entities.Role;
import com.example.pc.entities.User;

public interface UserService {
    void deleteAllusers();
    void deleteAllRoles();
    User saveUser(User user);
    User findUserByUsername(String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
}