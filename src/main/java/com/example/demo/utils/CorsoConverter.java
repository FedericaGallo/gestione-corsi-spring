package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;

import java.util.ArrayList;
import java.util.List;

public class CorsoConverter {
    public static CorsoDTO entityToDTO (Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDiscenti(corso.getDiscenti());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setId(corso.getId());
        Docente docente = corso.getDocente();
        DocenteDTO docenteDTO = new DocenteDTO();
        docenteDTO.setId(docente.getId());
        docenteDTO.setNome(docente.getNome());
        docenteDTO.setCognome(docente.getCognome());
        corsoDTO.setDocenteDTO(docenteDTO);

        return corsoDTO;
    }
    public static CorsoDTO entityToDTO2 (Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDiscenti(corso.getDiscenti());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setId(corso.getId());
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDocenteDTONome(corso.getDocenteNome());
        corsoDTO.setDocenteDTONome(corso.getDocenteCognome());
        corsoDTO.setDocenteDTOId(corso.getDocenteId());
        return corsoDTO;
    }
}
