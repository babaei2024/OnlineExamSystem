package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

}
