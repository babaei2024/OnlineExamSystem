package com.example.onlineexamsystem.Service;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.Role;
import com.example.onlineexamsystem.Repository.RoleRepository;
import com.example.onlineexamsystem.Repository.UserRepository;
import org.apache.catalina.User;
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

    Role role = roleRepository.findByRoleName(roleName);  //متدد ثبت کاربر جدید همراه با نقش
        if (role == null)   {
            role = new Role();
            role.setRoleName(roleName);
            role = roleRepository.save(role);
        }
        user.setRole(role);   // اختصاص نقش به کاربر
        return userRepository.save(user);  // ذخیره کاربر در دیتابیس
    }
    public AppUser login(String email, String password) {
        List<AppUser> users = userRepository.findAll();
        for (AppUser u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        throw new RuntimeException("ایمیل یا پسورد اشتباه است");
    }
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

