package com.example.OnlineQuizSystem.service;

import com.example.OnlineQuizSystem.dto.QuizRequestDTO;
import com.example.OnlineQuizSystem.model.Quiz;
import com.example.OnlineQuizSystem.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz convertDtoTOQuiz(QuizRequestDTO dto){
        Quiz q=new Quiz();
        q.setTitle(dto.getTitle());
        q.setDescription(dto.getDescription());
        return q;
    }

    public Quiz createQuiz(QuizRequestDTO request) {
        return quizRepository.save(convertDtoTOQuiz(request));
    }

    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz Not Found"));
    }

    public Quiz updateQuize(Long id, QuizRequestDTO request) {
        Quiz quiz=getQuizById(id);
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        return quizRepository.save(quiz);
    }

    public String deleteAllQuiz() {
        quizRepository.deleteAll();
        return "deleted successfully";
    }

    public String deleteQuizById(Long id) {
        quizRepository.deleteById(id);
        return "deleted successfully";
    }

}
