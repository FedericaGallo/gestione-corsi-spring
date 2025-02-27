package com.example.demo.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {

    @Email(message="not valid")
    @NotEmpty(message="First is mandatory")
    @NotBlank(message="First is mandatory")
    private String email;
    @Size(min= 8, message="too short")
    @NotEmpty(message="password is mandatory")
    @NotBlank(message="password is mandatory")
    private String password;

    // Getter e Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Builder
    public static class RegistrationRequestBuilder {
        private String email;
        private String password;

        public RegistrationRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public RegistrationRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegistrationRequest build() {
            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setEmail(this.email);
            registrationRequest.setPassword(this.password);
            return registrationRequest;
        }
    }
}
