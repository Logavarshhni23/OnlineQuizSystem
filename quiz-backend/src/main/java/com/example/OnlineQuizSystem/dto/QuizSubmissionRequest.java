package com.example.OnlineQuizSystem.dto;

import lombok.Data;

import java.util.Map;

@Data
public class QuizSubmissionRequest {

    private Long quizId;

    private Map<Long,String> answers;
}