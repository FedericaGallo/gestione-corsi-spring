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
    private DocenteDTO docenteDTO;

    public CorsoDTO(){
        this.docenteDTO=new DocenteDTO();
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
    public void setId(Integer id){
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
    public List<Discente> getDiscenti(){
        return this.discenti;
    }
    public void setDiscenti(List<Discente> discenti){
        this.discenti = discenti;
    }
    public int getId(){
        return id;
    }
    public void setDocenteDTO(DocenteDTO docenteDTO){
        this.docenteDTO=docenteDTO;
    }
    public DocenteDTO getDocenteDTO(){
       return this.docenteDTO;
    }
    public void setDocenteDTOId(Integer id){
        this.docenteDTO.setId(id);
    }
    public void setDocenteDTONome(String nome){
        this.docenteDTO.setNome(nome);
    }
    public void setDocenteDTOCognome(String cognome){
        this.docenteDTO.setCognome(cognome);
    }
    public Integer getDocenteDTOId(){
       return this.docenteDTO.getId();
    }
    public String getDocenteDTONome(){
       return this.docenteDTO.getNome();
    }
    public String getDocenteDTOCognome(){
       return this.docenteDTO.getCognome();
    }
}
