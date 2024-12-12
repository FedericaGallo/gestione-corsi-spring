package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "corsotest")

public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nome_corso")
    private String nomeCorso;
    @Column(name="data_inizio")
    private LocalDate dataInizio;
    private String durata;
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
    @ManyToMany
    @JoinTable(name = "corso_discente",
           joinColumns = @JoinColumn(name = "corso_id"),
           inverseJoinColumns = @JoinColumn(name="discente_id"))
    private List<Discente> discenti;

    public Corso(){
        this.docente=new Docente();
    }
    public void setNomeCorso(String nomeCorso){
        this.nomeCorso = nomeCorso;
    }
    public void setDataInizio(LocalDate dataInizio){
        this.dataInizio = dataInizio;
    }
    public void setDurata(String durata){
        this.durata = durata;
    }
    public void setDocente(Docente docente){
        this.docente = docente;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNomeCorso(){
        return nomeCorso;
    }
    public LocalDate getDataInizio(){
        return dataInizio;
    }
    public String getDurata(){
        return durata;
    }
    public Integer getDocenteId(){
        return docente.getId();
    }
    public Docente getDocente(){
        return docente;
    }
    public List<Discente> getDiscenti(){
        return this.discenti;
    }
    public void setDiscenti(List<Discente> discenti){
        this.discenti = discenti;
    }
    public void setDocenteId(Integer id){
        this.docente.setId(id);
    }
    public void setDocenteNome(String nome){
        this.docente.setNome(nome);
    }
    public void setDocenteCognome(String cognome){
        this.docente.setCognome(cognome);
    }
    public String getDocenteCognome(){
        return docente.getCognome();
    }
    public String getDocenteNome(){
        return docente.getNome();
    }
    public Integer getId(){
        return id;
    }
}