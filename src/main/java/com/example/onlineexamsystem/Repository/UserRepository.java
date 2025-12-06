package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser , Long> {

    boolean existsByEmail(String email);

    AppUser findByEmail(String email);

    // برای گزارش‌ها
    List<AppUser> findByRole(Role role);
}
