package com.scalablesscripts.auth.repository;

import com.scalablesscripts.auth.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
    // Aucun autre code ici
}
