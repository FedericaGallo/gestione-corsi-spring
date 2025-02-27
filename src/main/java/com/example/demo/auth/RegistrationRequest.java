package com.example.demo.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    @NotEmpty(message="Firstname is mandatory")
    @NotBlank(message="Firstname is mandatory")
    private String nome;
    @NotEmpty(message="Lastname is mandatory")
    @NotBlank(message="LastName is mandatory")
    private String cognome;
    @Email(message="not valid")
    @NotEmpty(message="First is mandatory")
    @NotBlank(message="First is mandatory")
    private String email;
    @Size(min= 8, message="too short")
    @NotEmpty(message="First is mandatory")
    @NotBlank(message="First is mandatory")
    private String password;


    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

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

}
/*La classe RegistrationRequest serve a rappresentare i dati che un utente
deve fornire per registrarsi al sistema. In altre parole, è un oggetto che raccoglie
tutte le informazioni necessarie per creare un nuovo account utente. */
