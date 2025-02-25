package com.example.demo.auth;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
//@Tag(name= "Authentication")
public class AuthenticationController {
private AuthenticationService service;
public AuthenticationController(AuthenticationService service){
    this.service = service;
}
@PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
) throws MessagingException {
    service.register(request);
    return ResponseEntity.accepted().build();
}
}
