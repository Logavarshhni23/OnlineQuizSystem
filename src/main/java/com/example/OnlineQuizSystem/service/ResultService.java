package com.example.OnlineQuizSystem.service;

import com.example.OnlineQuizSystem.dto.ResultResponse;
import com.example.OnlineQuizSystem.model.Result;
import com.example.OnlineQuizSystem.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result saveResult(Result result){
        return resultRepository.save(result);
    }

    public List<Result> getResultsByUser(Long userId){
        return resultRepository.findByUserId(userId);
    }

    public ResultResponse calculateScore(int score,int totalQuestions){
        return new ResultResponse(score,totalQuestions);
    }
}