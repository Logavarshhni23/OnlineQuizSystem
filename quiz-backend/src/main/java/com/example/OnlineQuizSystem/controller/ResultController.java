package com.example.OnlineQuizSystem.controller;

import com.example.OnlineQuizSystem.dto.ResultResponse;
import com.example.OnlineQuizSystem.model.Result;
import com.example.OnlineQuizSystem.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping
    public Result saveResult(@RequestBody Result result){
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

        return resultService.calculateScore(score,totalQuestions);
    }
}