package com.example.OnlineQuizSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Cannot be empty")
    private String name;
    @NotBlank(message = "Cannot be empty")
    @Email(message = "Invalid Email format")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Cannot be empty")
    private String password;
    private String role="USER";
}
