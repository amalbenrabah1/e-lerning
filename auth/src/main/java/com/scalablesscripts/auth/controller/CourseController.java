package com.scalablesscripts.auth.controller;

import com.scalablesscripts.auth.model.Course;
import com.scalablesscripts.auth.repository.CourseRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    private static final String UPLOAD_DIR = "/path/to/upload/directory/";

    @PostMapping("/add")
    public String addCourse(@RequestParam String title, @RequestParam String description,@RequestParam String subject,@RequestParam String level, @RequestParam("file") MultipartFile file) {
        try {
            // Vérifier si le répertoire de téléchargement existe, sinon le créer
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Sauvegarder le fichier PDF dans le répertoire de téléchargement
            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            // Sauvegarder les informations du cours dans la base de données
            Course course = new Course(title, description,subject,level, file.getOriginalFilename());
            courseRepository.save(course);

            return "Cours ajouté avec succès!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de l'ajout du cours.";
        }
    }

    @GetMapping("/")
    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Méthode pour récupérer un fichier PDF d'un cours
    @GetMapping("/download/{courseId}")
    public ResponseEntity<FileSystemResource> downloadCourseFile(@PathVariable String courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
    
        if (course != null) {
            File file = new File(UPLOAD_DIR + course.getFilename());  // Utilisation de getFilename() ici
            System.out.println("Téléchargement du fichier: " + file.getAbsolutePath());
            if (file.exists()) {
                FileSystemResource resource = new FileSystemResource(file);
                return ResponseEntity.ok()
                        .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();  // Si le fichier n'est pas trouvé
            }
        } else {
            return ResponseEntity.notFound().build();  // Si le cours n'est pas trouvé
        }
    }
    
    
    
}
