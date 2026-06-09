package com.example.OnlineQuizSystem.service;

import com.example.OnlineQuizSystem.dto.QuizRequestDTO;
import com.example.OnlineQuizSystem.dto.QuizResponseDTO;
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

    public QuizResponseDTO convertQuizToDTO(Quiz quiz){
        QuizResponseDTO dto=new QuizResponseDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        return dto;
    }

    public Quiz createQuiz(QuizRequestDTO request) {
        return quizRepository.save(convertDtoTOQuiz(request));
    }

    public List<QuizResponseDTO> getAllQuiz() {
        return quizRepository.findAll()
                .stream()
                .map(this::convertQuizToDTO)
                .toList();
    }

    public QuizResponseDTO getQuizById(Long id) {
        Quiz quiz= getQuizEntityById(id);
        return convertQuizToDTO(quiz);
    }

    public Quiz updateQuize(Long id, QuizRequestDTO request) {
        Quiz quiz=getQuizEntityById(id);
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

    public Quiz getQuizEntityById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz Not Found"));
    }

}
