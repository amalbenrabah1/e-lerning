package com.scalablesscripts.auth.service;

import com.scalablesscripts.auth.model.User;
import com.scalablesscripts.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    // Envoi d'un email générique
    public void sendEmail(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("amalbenrabah40@gmail.com");  // Remplacez par votre adresse email
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    // Envoi d'un lien de réinitialisation du mot de passe
    public boolean sendResetLink(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }

        // Générer un token unique de réinitialisation

        String resetLink = "http://localhost:8081/signup";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Réinitialisation du mot de passe");
            message.setText("Bonjour ! 👋\n" + //
                                "\n" + //
                                "Il semble que vous ayez oublié votre mot de passe. Pas de souci, cela arrive à tout le monde ! 😅\n" + //
                                "\n" + //
                                "Pour réinitialiser votre mot de passe, cliquez simplement sur le lien ci-dessous 👇 :\n" +resetLink +//
                                "\n" + //
                                "Réinitialiser mon mot de passe\n" + //
                                "\n" + //
                                "Ce lien vous permettra de définir un nouveau mot de passe et de retrouver l'accès à votre compte en un rien de temps. 🔑\n" + //
                                "\n" + //
                                "Si vous n'avez pas demandé cette réinitialisation, ignorer ce message. 🛑\n" + //
                                "\n" + //
                                "N'hésitez pas à nous contacter si vous avez des questions ! 😊\n" + //
                                "\n" + //
                                "Cordialement,\n" + //
                                "L’équipe Kirayeti  🚀"); 
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendVerificationEmail(String Email) {
        String subject = "Vérification de votre email";
        String link="http://localhost:8081/login";
        String text = "Bonjour et bienvenue sur Kirayeti ! 🎉\n\n" +
            "Merci de vous être inscrit sur notre plateforme. Nous sommes ravis de vous avoir parmi nous ! 😊\n\n" +
            "Pour compléter votre inscription et commencer à profiter de toutes les fonctionnalités, \n\n"+
            "veuillez cliquer sur le lien suivant pour vous connecter :\n" +
            "Compléter mon inscription\n\n" +link +"\n\n" +
            "Nous avons hâte de vous voir utiliser Kirayeti pour toutes vos prochaines aventures ! 🚀\n\n" +
            "Si vous avez des questions, n'hésitez pas à nous contacter. 📩\n\n" +
            "Cordialement,\n" +
            "L'équipe Kirayeti ✨";


        sendEmail(Email, subject, text);
        return true;
    }
}
