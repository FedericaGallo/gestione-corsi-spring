package com.example.demo.DTO;

import com.example.demo.entity.Corso;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscenteDTO {
    private Integer id;
    private String nome;
    private String cognome;
    private String matricola;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataDiNascita;
    private List<CorsoDTO> corsiSeguiti;

    /*public DiscenteDTO(){
        this.corsiSeguiti=new ArrayList<>();
    }*/
    public void setNome(String nome) {
        this.nome = nome;
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

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setDataDiNascita(LocalDate data) {
        this.dataDiNascita = data;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setCorsiSeguiti(List<CorsoDTO> corsiSeguiti) {
        this.corsiSeguiti = corsiSeguiti;
    }

    public List<CorsoDTO> getCorsiSeguiti() {
        return corsiSeguiti;
    }

}
