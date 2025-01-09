package com.scalablesscripts.auth.controller;

import com.scalablesscripts.auth.model.Quiz;
import com.scalablesscripts.auth.repository.QuizRepository;
import com.scalablesscripts.auth.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "localhost:8081")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }
    
    @GetMapping("/level/{level}")
    public ResponseEntity<List<Quiz>> getQuizzesByLevel(@PathVariable String level) {
        List<Quiz> quizzes = quizRepository.findByLevel(level);
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping("/subject/{subject}/level/{level}")
    public ResponseEntity<List<Quiz>> getQuizzesBySubjectAndLevel(
        @PathVariable String subject,
        @PathVariable String level) {
    List<Quiz> quizzes = quizService.findQuizzesBySubjectAndLevel(subject, level);
    return ResponseEntity.ok(quizzes);
}

    @GetMapping("/quizzeslist")
    public List<Quiz> getQuizzes(@RequestParam(required = false) String level, 
                                  @RequestParam(required = false) String subject) {
        if (level != null && subject != null) {
            return quizRepository.findByLevelAndSubject(level, subject);
        } else if (level != null) {
            return quizRepository.findByLevel(level);
        } else if (subject != null) {
            return quizRepository.findBySubject(subject);
        } else {
            return quizRepository.findAll();
        }
    }
}
