package com.example.demo.auth;

import com.example.demo.email.EmailService;
import com.example.demo.email.EmailTemplateName;
import com.example.demo.entity.Token;
import com.example.demo.entity.Utente;
import com.example.demo.repository.RuoloRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UtenteRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationService {
    private EmailService emailService;
    private RuoloRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UtenteRepository utenteRepository;
    private TokenRepository tokenRepository;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;
    public AuthenticationService(RuoloRepository repository, PasswordEncoder passwordEncoder, UtenteRepository utenteRepository, TokenRepository tokenRepository, EmailService emailService){
        this.roleRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.utenteRepository =utenteRepository;
        this.tokenRepository =tokenRepository;
        this.emailService= emailService;
    }
    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByNome("USER")
                .orElseThrow(()-> new IllegalStateException("not found"));
        var user = Utente.builder()
                .nome(request.getNome())
                .cognome(request.getCognome())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .ruoli(List.of(userRole))
                .build();
        utenteRepository.save(user);
        sendValidationEmail(user);
        System.out.print("register");
    }

    private void sendValidationEmail(Utente utente) throws MessagingException {
        var newToken = generateAndSaveActivatationToken(utente);
        emailService.sendEmail(
                utente.getEmail(),
                utente.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
        System.out.print("email");

    }


    private String generateAndSaveActivatationToken(Utente utente){
        String generatedToken = generateAndSaveActivatationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .utente(utente)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateAndSaveActivatationCode(int length){
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i <length; i++){
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
