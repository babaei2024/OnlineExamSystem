package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.Role;
import com.example.onlineexamsystem.Repository.RoleRepository;
import com.example.onlineexamsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<AppUser> getAllUsers(){
        return userRepository.findAll();
    }

    public AppUser registerUser(AppUser user, String roleName) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("ایمیل تکراری است");
        }

        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = new Role();
            role.setRoleName(roleName);
            role = roleRepository.save(role);
        }

        user.setRole(role);
        return userRepository.save(user);
    }

    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
