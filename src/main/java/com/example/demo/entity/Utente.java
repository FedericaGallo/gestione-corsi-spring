package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Entity
@Table(name= "utenti")
@EntityListeners(AuditingEntityListener.class)
public class Utente implements UserDetails, Principal {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;
    private String cognome;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean accountLocked;
    private Boolean enabled;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Ruolo> ruoli;

    public Utente(){}
    public Utente(String nome, String cognome,String eta, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        //this.ruolo = new Ruolo();
        //this.ruolo.setNome("USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.ruoli
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getNome()))
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    // Getter e Setter per id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    // Getter e Setter per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter per cognome
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    // Getter e Setter per email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e Setter per password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter e Setter per ruolo
    public List<Ruolo> getRuolo() {
        return ruoli;
    }

    public void setRuolo( List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }
    // Getter e Setter per accountLocked
    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    // Getter e Setter per enabled
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID id;
        private String nome;
        private String cognome;
        private String email;
        private String password;
        private Boolean accountLocked;
        private Boolean enabled;
        private LocalDate createdDate;
        private LocalDate lastModifiedDate;
        private List<Ruolo> ruoli;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder cognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder accountLocked(Boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder createdDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder lastModifiedDate(LocalDate lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public Builder ruoli(List<Ruolo> ruoli) {
            this.ruoli = ruoli;
            return this;
        }

        public Utente build() {
            Utente utente = new Utente();
            utente.id = this.id;
            utente.nome = this.nome;
            utente.cognome = this.cognome;
            utente.email = this.email;
            utente.password = this.password;
            utente.accountLocked = this.accountLocked;
            utente.enabled = this.enabled;
            utente.createdDate = this.createdDate;
            utente.lastModifiedDate = this.lastModifiedDate;
            utente.ruoli = this.ruoli;
            return utente;
        }
    }
    public String fullName(){
        return nome + cognome;
    }
}
