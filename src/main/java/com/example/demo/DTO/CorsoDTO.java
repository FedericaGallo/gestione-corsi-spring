package com.example.demo.DTO;

import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CorsoDTO {
    private Integer id;
    private String nomeCorso;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate dataInizio;
    private String durata;
    private List<Discente> discenti;
    private String nomeDocenteDTO;
    private String cognomeDocenteDTO;
    private Integer idDocenteDTO;


    public void setNomeCorso(String nomeCorso){
        this.nomeCorso = nomeCorso;
    }
    public void setDataInizio(LocalDate dataInizio){
        this.dataInizio = dataInizio;
    }
    public void setDurata(String durata){
        this.durata = durata;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void setDiscenti(List<Discente> discenti){
        this.discenti = discenti;
    }
    public void setNomeDocenteDTO(String nome){
        this.nomeDocenteDTO=nome;
    }
    public void setCognomeDocenteDTO(String cognome){
        this.cognomeDocenteDTO=cognome;
    }
    public void setIdDocenteDTO(Integer id){
        this.idDocenteDTO=id;
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
    public List<Discente> getDiscenti(){
        return this.discenti;
    }
    public int getId(){
        return id;
    }
    public String getNomeDocenteDTO(){
        return nomeDocenteDTO;
    }
    public String getCognomeDocenteDTO(){
        return cognomeDocenteDTO;
    }
    public Integer getIdDocenteDTO(){
        return idDocenteDTO;
    }

}
