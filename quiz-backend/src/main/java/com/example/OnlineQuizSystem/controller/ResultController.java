package com.example.OnlineQuizSystem.controller;

import com.example.OnlineQuizSystem.dto.ResultResponse;
import com.example.OnlineQuizSystem.model.Result;
import com.example.OnlineQuizSystem.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping
    public Result saveResult(@RequestBody Map<String, Object> body) {
        Result result = new Result();
        result.setScore((Integer) body.get("score"));
        result.setTotalQuestions((Integer) body.get("totalQuestions"));
        result.setSubmittedAt(LocalDateTime.now());
        return resultService.saveResult(result);
    }

    @GetMapping("/user/{userId}")
    public List<Result> getResults(@PathVariable Long userId){
        return resultService.getResultsByUser(userId);
    }

    @GetMapping("/score")
    public ResultResponse score(
            @RequestParam int score,
            @RequestParam int totalQuestions){
        return resultService.calculateScore(score, totalQuestions);
    }
}