package com.example.demo.auth;

import java.util.Date;
import java.util.List;

public class AuthenticationResponse {
    private String token;
    private Date expirationDate;
    private String ruoli;
    // Getter Setter
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setRuoli(String ruoli) {
        this.ruoli = ruoli;
    }
    public String getRuoli() {
        return ruoli;
    }

    // Builder
    public static class AuthenticationResponseBuilder {
        private String token;
        private Date expirationDate;
        private String ruoli;

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder expirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public AuthenticationResponseBuilder ruoli(String ruoli) {
            this.ruoli = ruoli;
            return this;
        }

        public AuthenticationResponse build() {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(this.token);
            authenticationResponse.setExpirationDate(this.expirationDate);
            authenticationResponse.setRuoli(this.ruoli);
            return authenticationResponse;
        }
    }

    // Metodo statico per ottenere il builder
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }
}
