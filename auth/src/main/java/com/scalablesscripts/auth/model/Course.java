package com.scalablesscripts.auth.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String title;
    private String description;
    private String subject;
    private String level;
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    private String filename; // Ajout de l'attribut filename

    // Constructeur
    public Course(String title, String description, String subject,String level, String filename) {
        this.title = title;
        this.description = description;
        this.filename = filename;
        this.subject = subject;
        this.level = level;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename; // Cette méthode permet d'obtenir le nom du fichier
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
