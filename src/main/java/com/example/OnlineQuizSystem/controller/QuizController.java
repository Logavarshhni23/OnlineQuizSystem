package com.example.OnlineQuizSystem.controller;

import com.example.OnlineQuizSystem.dto.QuizRequestDTO;
import com.example.OnlineQuizSystem.dto.QuizResponseDTO;
import com.example.OnlineQuizSystem.model.Quiz;
import com.example.OnlineQuizSystem.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //Create Quiz
    @PostMapping("/create")
    public Quiz createQuiz(@RequestBody QuizRequestDTO request){
        return quizService.createQuiz(request);
    }

    //getAll quizes
    @GetMapping("/get")
    public List<QuizResponseDTO> getAllQuiz(){
        return quizService.getAllQuiz();
    }

    //get by id
    @GetMapping("/get/{id}")
    public QuizResponseDTO getQuizById(@PathVariable("id") Long id){
        return quizService.getQuizById(id);
    }

    //update quiz
    @PutMapping("/update/{id}")
    public Quiz updateQuiz(@PathVariable Long id,@RequestBody QuizRequestDTO request){
        return quizService.updateQuize(id,request);
    }

    //delete the all quiz
    @DeleteMapping("/delete")
    public String deleteAllQuiz(){
        return quizService.deleteAllQuiz();
    }

    //delete the quiz by id
    @DeleteMapping("/delete/{id}")
    public String deleteQuizByID(@PathVariable Long id){
        return quizService.deleteQuizById(id);
    }

    @GetMapping("/crsf")
    public CsrfToken gettoken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }


}
