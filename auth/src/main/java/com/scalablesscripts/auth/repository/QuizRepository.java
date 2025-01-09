package com.scalablesscripts.auth.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scalablesscripts.auth.model.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByLevel(String level);
    List<Quiz> findBySubject(String subject);
    List<Quiz> findBySubjectAndLevel(String subject, String level);
    List<Quiz> findByLevelAndSubject(String level, String subject);

}
