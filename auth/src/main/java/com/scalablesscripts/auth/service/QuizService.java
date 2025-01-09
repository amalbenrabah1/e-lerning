package com.scalablesscripts.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalablesscripts.auth.model.Quiz;
import com.scalablesscripts.auth.repository.QuizRepository;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public List<Quiz> findQuizzesBySubjectAndLevel(String subject, String level) {
        return quizRepository.findBySubjectAndLevel(subject, level);
    }
}

