package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "discentetest")
public class Discente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    private String matricola;
    @Column(name= "data_nascita")
    private LocalDate dataDiNascita;
    @ManyToMany(
            mappedBy = "discenti"
    )

    private List<Corso> corsiSeguiti;

    /*public Discente(){
        this.corsiSeguiti=new ArrayList<>();
    }*/
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMatricola(String matricola) {this.matricola = matricola;}

    public String getMatricola() {return matricola; }

    public void setDataDiNascita(LocalDate data) {this.dataDiNascita = data;}

    public LocalDate getDataDiNascita() {return dataDiNascita; }

    public void setCorsiSeguiti(List<Corso> corsiSeguiti){
        this.corsiSeguiti=corsiSeguiti;
    }

    public List<Corso> getCorsiSeguiti(){
       return corsiSeguiti;
    }



}
