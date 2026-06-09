package com.example.OnlineQuizSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultResponse {

    private int score;

    private int totalQuestions;
}