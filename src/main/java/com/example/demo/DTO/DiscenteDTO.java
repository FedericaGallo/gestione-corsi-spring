package com.example.demo.DTO;

import com.example.demo.entity.Corso;

import java.time.LocalDate;
import java.util.List;

public class DiscenteDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private String matricola;
    private LocalDate dataDiNascita;
    private List<Corso> corsiSeguiti;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public String prova(){
        return "prova";
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getid() {
        return id;
    }

    public void setMatricola(String matricola) {this.matricola = matricola;}

    public String getMatricola() {return matricola; }

    public void setDataDiNascita(LocalDate data) {this.dataDiNascita = data;}

    public LocalDate getDataDiNascita() {return dataDiNascita; }

}
