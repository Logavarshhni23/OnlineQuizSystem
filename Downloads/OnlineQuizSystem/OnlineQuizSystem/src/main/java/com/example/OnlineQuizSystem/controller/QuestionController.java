package com.example.OnlineQuizSystem.controller;

import com.example.OnlineQuizSystem.model.Question;
import com.example.OnlineQuizSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService s;



    @GetMapping
    public List<Question> getAllQuestions(){
        return s.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id){
        return s.getQuestionById(id);
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question question){
        return s.addQuestion(question);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id,
                                   @RequestBody Question question) {
        return s.updateQuestion(id, question);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id){
        s.deleteQuestion(id);
        return "Question Deleted Successfully";
    }
}