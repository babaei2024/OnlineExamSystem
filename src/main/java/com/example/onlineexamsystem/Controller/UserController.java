package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.LoginRequest;
import com.example.onlineexamsystem.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")  // اجازه دسترسی از فرانت
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public List<AppUser> getUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/register")
    public AppUser regidterUser(
            @RequestBody AppUser user,
            @RequestParam String role) {
        return userService.registerUser(user,role);
    }
    @PostMapping("/login")
    public AppUser login (@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }
}
