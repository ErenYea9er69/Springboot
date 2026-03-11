package com.example.pc.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pc.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}