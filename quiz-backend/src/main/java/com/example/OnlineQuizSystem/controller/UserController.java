package com.example.OnlineQuizSystem.controller;

import com.example.OnlineQuizSystem.dto.AuthResponse;
import com.example.OnlineQuizSystem.dto.LoginRequest;
import com.example.OnlineQuizSystem.dto.RegisterRequest;
import com.example.OnlineQuizSystem.model.User;
import com.example.OnlineQuizSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService authService;

    //register
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    //login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    //get all users (admin only)
    @GetMapping("/users")
    public List<Map<String, Object>> getAllUsers() {
        return authService.getAllUsers();
    }
}