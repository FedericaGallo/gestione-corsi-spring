package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime validatedAt;
    @ManyToOne
    @JoinColumn(name="utenteId", nullable = false)
    private Utente utente;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String token;
        private LocalDateTime createdAt;
        private LocalDateTime expiredAt;
        private LocalDateTime validatedAt;
        private Utente utente;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder expiredAt(LocalDateTime expiredAt) {
            this.expiredAt = expiredAt;
            return this;
        }

        public Builder validatedAt(LocalDateTime validatedAt) {
            this.validatedAt = validatedAt;
            return this;
        }

        public Builder utente(Utente utente) {
            this.utente = utente;
            return this;
        }

        public Token build() {
            Token token = new Token();
            token.id = this.id;
            token.token = this.token;
            token.createdAt = this.createdAt;
            token.expiredAt = this.expiredAt;
            token.validatedAt = this.validatedAt;
            token.utente = this.utente;
            return token;
        }
    }
}
