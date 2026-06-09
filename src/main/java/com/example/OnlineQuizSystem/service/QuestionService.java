package com.example.OnlineQuizSystem.service;

import com.example.OnlineQuizSystem.model.Question;
import com.example.OnlineQuizSystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository r;

    public List<Question> getAllQuestions() {
        return r.findAll();
    }

    public Question getQuestionById(Long id) {
        return r.findById(id).orElse(null);
    }


    public Question addQuestion(Question question) {
        return r.save(question);
    }


    public Question updateQuestion(Long id, Question updatedQuestion) {
        Question q = r.findById(id).orElseThrow();

        q.setQuestionText(updatedQuestion.getQuestionText());
        q.setOptionA(updatedQuestion.getOptionA());
        q.setOptionB(updatedQuestion.getOptionB());
        q.setOptionC(updatedQuestion.getOptionC());
        q.setOptionD(updatedQuestion.getOptionD());
        q.setCorrectAnswer(updatedQuestion.getCorrectAnswer());

        return r.save(q);
    }

    public void deleteQuestion(Long id) {
        r.deleteById(id);
    }
}
