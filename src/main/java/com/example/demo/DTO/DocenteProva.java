package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class DocenteProva {
    private Integer id;
    private String nome;
    private String cognome;
    private String photo;


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getNome() {
        return nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
