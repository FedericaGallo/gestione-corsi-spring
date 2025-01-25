package com.example.demo.utils;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteProva;
import com.example.demo.entity.Docente;

public class DocenteConverter {

    public static DocenteProva entityToDTOprova (Docente docente){
        DocenteProva docenteProva = new DocenteProva();
        docenteProva.setNome(docente.getNome());
        docenteProva.setCognome(docente.getCognome());
        docenteProva.setId(docente.getId());
        docenteProva.setPhoto("Newton.jpg");
        /*DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setId(docente.getId());
        for (int i = 0; i<docente.getCorsi().size(); i++){
            docenteDTO.addCorsi(CorsoConverter.entityToDTO(docente.getCorsi().get(i)));
        }*/
        return docenteProva;
    }

    public static DocenteDTO entityToDTO (Docente docente){
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setId(docente.getId());
        return docenteDTO;
    }

    public static DocenteDTO entityToDTOGetDocente (Docente docente){
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        docenteDTO.setId(docente.getId());
        for (int i = 0; i<docente.getCorsi().size(); i++){
            docenteDTO.addCorsi(CorsoConverter.entityToDTOGetDocente(docente.getCorsi().get(i)));
        }
        return docenteDTO;
    }
}
