package com.example.demo.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(name="data_fine")
    private LocalDate dataFine;
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "corso_discente",
           joinColumns = @JoinColumn(name = "corso_id"),
           inverseJoinColumns = @JoinColumn(name="discente_id"))
    private List<Discente> discenti;

    public Corso(){
        this.docente=new Docente();
        this.discenti=new ArrayList<>();
    }
    public void setNomeCorso(String nomeCorso){
        this.nomeCorso = nomeCorso;
    }
    public void setDataInizio(LocalDate dataInizio){
        this.dataInizio = dataInizio;
    }
    public void setDataFine(LocalDate dataFine){
        this.dataFine = dataFine;
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
    public LocalDate getDataFine(){
        return dataFine;
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
    public String getDocenteNome() {
        return  docente.getNome();
    }
    public String getDocenteCognome() {
        return docente.getCognome();
    }
    public Integer getId(){
        return id;
    }
    public void addDiscenti(ArrayList<Discente> discenti){
        for (Discente d : discenti ){
            this.discenti.add(d);
        }
    }
}
