package com.example.OnlineQuizSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question cannot be empty")
    private String questionText;

    @NotBlank(message = "Option A cannot be empty")
    private String optionA;

    @NotBlank(message = "Option B cannot be empty")
    private String optionB;

    @NotBlank(message = "Option C cannot be empty")
    private String optionC;

    @NotBlank(message = "Option D cannot be empty")
    private String optionD;

    @NotBlank(message = "Correct answer cannot be empty")
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}