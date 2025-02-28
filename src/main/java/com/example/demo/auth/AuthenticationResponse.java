package com.example.demo.auth;

import java.util.Date;

public class AuthenticationResponse {
    private String token;
    private Date expirationDate;
    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }

    // Builder
    public static class AuthenticationResponseBuilder {
        private String token;
        private Date expirationDate;

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder expirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public AuthenticationResponse build() {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(this.token);
            authenticationResponse.setExpirationDate(this.expirationDate);
            return authenticationResponse;
        }
    }

    // Metodo statico per ottenere il builder
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }
}
