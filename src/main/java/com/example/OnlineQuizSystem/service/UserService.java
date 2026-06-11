package com.example.OnlineQuizSystem.service;

import com.example.OnlineQuizSystem.dto.AuthResponse;
import com.example.OnlineQuizSystem.dto.LoginRequest;
import com.example.OnlineQuizSystem.dto.RegisterRequest;
import com.example.OnlineQuizSystem.model.User;
import com.example.OnlineQuizSystem.repository.UserRepository;
import com.example.OnlineQuizSystem.security.JwtSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtSecurity jwtSecurity;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //register
    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "User Created Successfully";
    }
    //login
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        String token = jwtSecurity.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getRole());
    }
}