// UserService.java
package com.scalablesscripts.auth.service;

import com.scalablesscripts.auth.model.User;
import com.scalablesscripts.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    

    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean registerUser(User user) {
        if (isUserExist(user.getEmail())) {
            return false;
        }

        if (!isValidRole(user.getRole())) {
            throw new IllegalArgumentException("Rôle invalide !");
        }


        userRepository.save(user);
        return true;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);  // Implémentation dans le repository
    }

    private boolean isValidRole(String role) {
        return role.equals("student") || role.equals("teacher") || role.equals("admin");
    }

    public String getRole(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? user.getRole() : null;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}