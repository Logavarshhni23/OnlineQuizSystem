package com.example.OnlineQuizSystem.controller;

import com.example.OnlineQuizSystem.dto.LoginRequest;
import com.example.OnlineQuizSystem.dto.RegisterRequest;
import com.example.OnlineQuizSystem.model.User;
import com.example.OnlineQuizSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //register
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}