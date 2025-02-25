package com.example.demo.entity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "Ruoli")
@EntityListeners(AuditingEntityListener.class)
public class Ruolo {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String nome;
    @ManyToMany(mappedBy = "ruoli")
    private List<Utente> utenti;
    private Boolean ruoloDiscente;
    private Boolean ruoloDocente;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;
    public Ruolo(){}
    public Ruolo(String nome, Boolean ruoloDiscente, Boolean ruoloDocente) {
        super();
        this.nome = nome;
        this.ruoloDiscente = ruoloDiscente;
        this.ruoloDocente = ruoloDocente;
    }
    // Getter e Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getRuoloDocente() {
        return ruoloDocente;
    }

    public void setRuoloDocente(Boolean ruoloDocente) {
        this.ruoloDocente = ruoloDocente;
    }

    public Boolean getRuoloDiscnete() {
        return ruoloDiscente;
    }

    public void setRuoloDiscente(Boolean ruoloDiscente) {
        this.ruoloDiscente = ruoloDiscente;
    }

    // Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String nome;
        private Boolean ruoloDocente;
        private Boolean ruoloDiscente;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder ruoloDocente(Boolean ruoloDocente) {
            this.ruoloDocente = ruoloDocente;
            return this;
        }

        public Builder ruoloDiscente(Boolean ruoloDiscente) {
            this.ruoloDiscente = ruoloDiscente;
            return this;
        }

        public Ruolo build() {
            Ruolo ruolo = new Ruolo();
            ruolo.setId(this.id);
            ruolo.setNome(this.nome);
            ruolo.setRuoloDocente(this.ruoloDocente);
            ruolo.setRuoloDiscente(this.ruoloDiscente);
            return ruolo;
        }
    }
}
