package com.example.demo.auth;

public class AuthenticationResponse {
    private String token;

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }

    // Builder
    public static class AuthenticationResponseBuilder {
        private String token;

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setToken(this.token);
            return authenticationResponse;
        }
    }

    // Metodo statico per ottenere il builder
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }
}
