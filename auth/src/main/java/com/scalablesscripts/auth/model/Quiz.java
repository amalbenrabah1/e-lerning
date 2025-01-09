package com.scalablesscripts.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "quizzes")
public class Quiz {
    @Id
    private String id;
    private String question;
    private String[] options;
    private int correctIndex;
    private String level;   // Ex : Beginner, Intermediate, Advanced
    private String subject;
    
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String[] getOptions() {
        return options;
    }
    public void setOptions(String[] options) {
        this.options = options;
    }
    public int getCorrectIndex() {
        return correctIndex;
    }
    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }

}