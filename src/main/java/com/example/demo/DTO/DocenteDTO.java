package com.example.demo.DTO;

import java.util.ArrayList;
import java.util.List;

public class DocenteDTO {
private Integer id;
private String nome;
private String cognome;
private List<CorsoDTO> corsi;

public DocenteDTO(){corsi = new ArrayList<>();}
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CorsoDTO> getCorsi() {
        return corsi;
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

    public void setCorsi(List<CorsoDTO> corsi) {
        this.corsi = corsi;
    }

    public void addCorsi(CorsoDTO corso){
        this.corsi.add(corso);
    }
}
