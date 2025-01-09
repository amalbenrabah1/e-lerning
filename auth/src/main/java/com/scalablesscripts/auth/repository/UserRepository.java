package com.scalablesscripts.auth.repository;

import com.scalablesscripts.auth.model.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
      User findByEmail(String email);
      
    boolean existsByEmail(String email);
    User findByVerificationToken(String token);

    Optional<User> findById(Long id);
    
}
