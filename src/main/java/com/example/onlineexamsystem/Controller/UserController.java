package com.example.onlineexamsystem.Controller;

import com.example.onlineexamsystem.Model.AppUser;
import com.example.onlineexamsystem.Model.LoginRequest;
import com.example.onlineexamsystem.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<AppUser> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(
            @RequestBody AppUser user,
            @RequestParam String role) {
            AppUser savedUser = userService.registerUser(user,role);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AppUser user = userService.findByEmail(request.getEmail());

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Incorrect password");
        }

        return ResponseEntity.ok(
                Map.of(
                        "message" , "Login successful" ,
                        "userId" ,user.getId(),
                        "fullName" , user.getFirstName() + " " + user.getLastName(),
                        "role" , user.getRole().getRoleName()));   // اطلاعات کاربر را برمی‌گردانیم
    }
}