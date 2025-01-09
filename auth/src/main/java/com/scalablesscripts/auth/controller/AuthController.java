package com.scalablesscripts.auth.controller;

import com.scalablesscripts.auth.model.User;
import com.scalablesscripts.auth.repository.UserRepository;
import com.scalablesscripts.config.JwtUtil;
import com.scalablesscripts.auth.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8081")  // Permet les requêtes du frontend sur le port 8081
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Route pour l'inscription d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email déjà utilisé !");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Assurez-vous que cette ligne est correcte
        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail());  // Envoi de l'email de vérification
        return ResponseEntity.ok("Utilisateur créé avec succès !");
    }

    // Route pour vérifier l'email de l'utilisateur
    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verifyEmail(@PathVariable String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token invalide.");
        }
        user.setEnabled(true);  // Marquer l'utilisateur comme vérifié
        user.setVerificationToken(null);  // Supprimer le token après vérification
        userRepository.save(user);

        return ResponseEntity.ok("Email vérifié avec succès.");
    }

    // Route pour la connexion d'un utilisateur
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    String email = credentials.get("email");
    String password = credentials.get("password");

    // Recherche de l'utilisateur par email
    User user = userRepository.findByEmail(email);

    // Vérification des identifiants
    if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
        return ResponseEntity.badRequest().body("Email ou mot de passe incorrect !");
    }

    // Création de la réponse sans JWT, en renvoyant les informations de l'utilisateur
    Map<String, String> response = new HashMap<>();
    response.put("id", user.getId());
    response.put("firstName", user.getFirstName());
    response.put("lastName", user.getLastName());
    response.put("email", user.getEmail());
    response.put("role", user.getRole());

    // Retourner une réponse OK avec les données utilisateur
    return ResponseEntity.ok(response);
}

    // Route pour envoyer un email de vérification de l'email
    @PostMapping("/sendVerificationEmail")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody String userEmail) {
        boolean emailSent = emailService.sendVerificationEmail(userEmail);

        if (emailSent) {
            return ResponseEntity.ok("Email de vérification envoyé !");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de l'envoi de l'email de vérification.");
        }
    }

    @PostMapping("/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
    String email = request.get("email");
    User user = userRepository.findByEmail(email);
    if (user == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Utilisateur introuvable.");
    }
    
    // Générer un token de réinitialisation spécifique
    String resetToken = UUID.randomUUID().toString();
    user.setPasswordResetToken(resetToken);
    userRepository.save(user);

    boolean emailSent = emailService.sendResetLink(email);
    if (emailSent) {
        return ResponseEntity.ok("Email envoyé avec succès !");
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de l'envoi de l'email de réinitialisation.");
    }
}

    // Endpoint pour récupérer les informations de l'utilisateur
    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);  // Remove "Bearer " prefix
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                User user = userRepository.findByEmail(email);
                if (user != null) {
                    return ResponseEntity.ok(user);  // Return user data
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication error");
    }


    // Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        System.out.println("Email reçu dans le backend : " + email);  // Vérifie l'email reçu
    
        // Recherche l'utilisateur par email
        User user = userRepository.findByEmail(email);  // Ou ton service
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Si utilisateur non trouvé
        }
    
        return ResponseEntity.ok(user);  // Si utilisateur trouvé
    }
    

    // Update user by email
    @PutMapping("/email/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (existingUser.isPresent()) {
            user.setEmail(email); // Ensure the email is the same
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete user by email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}