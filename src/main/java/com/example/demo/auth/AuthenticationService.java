package com.example.demo.auth;

import com.example.demo.email.EmailService;
import com.example.demo.email.EmailTemplateName;
import com.example.demo.entity.Token;
import com.example.demo.entity.Utente;
import com.example.demo.repository.RuoloRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.security.JwtService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationService {
    private EmailService emailService;
    private RuoloRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UtenteRepository utenteRepository;
    private TokenRepository tokenRepository;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public AuthenticationService(RuoloRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 UtenteRepository utenteRepository,
                                 TokenRepository tokenRepository,
                                 EmailService emailService,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService) {
        this.roleRepository = repository;
        this.passwordEncoder = passwordEncoder;
        this.utenteRepository = utenteRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByNome("USER")
                .orElseThrow(() -> new IllegalStateException("not found"));
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
    }


    private String generateAndSaveActivatationToken(Utente utente) {
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

    private String generateAndSaveActivatationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((Utente) auth.getPrincipal());
        claims.put("fullName", user.fullName());
        var jwtToken = jwtService.generateToken(claims, user);
        Date date = jwtService.getExpirationDate(jwtToken);
        System.out.println("Expiration Date: " + date);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiredAt())) {
            sendValidationEmail(savedToken.getUtente());
            throw new RuntimeException("expired");
        }
        var user = utenteRepository.findById(savedToken.getUtente().getId())
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        user.setEnabled(true);
        utenteRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);

    }


}
