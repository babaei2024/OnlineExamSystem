package com.example.onlineexamsystem.Repository;

import com.example.onlineexamsystem.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser , Long> {
    boolean existsByEmail(String email);
}
