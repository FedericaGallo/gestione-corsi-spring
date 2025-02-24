package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "docentetest")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cognome;
    @Column(columnDefinition = "TEXT")
    private String descrizione;
    @OneToMany(mappedBy = "docente", cascade = CascadeType.REMOVE)
    private List<Corso> corsi;

    public Docente() {
        this.corsi = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Corso> getCorsi() {
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescrizione(){
        return descrizione;
    }
    public void setDescrizione(String descrizione){
        this.descrizione = descrizione;
    }
    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }
    public void addCorsi(Corso corso){
        this.corsi.add(corso);
    }

}
