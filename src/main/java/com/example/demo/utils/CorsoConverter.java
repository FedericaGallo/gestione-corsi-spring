package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;

import java.util.ArrayList;
import java.util.List;

public class CorsoConverter {
    public static CorsoDTO entityToDTOGetDocente (Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setId(corso.getId());

        return corsoDTO;
    }

    public static CorsoDTO entityToDTOGetDiscente (Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setId(corso.getId());
        corsoDTO.setNomeDocenteDTO(corso.getDocenteNome());
        corsoDTO.setCognomeDocenteDTO(corso.getDocenteCognome());
        corsoDTO.setIdDocenteDTO(corsoDTO.getIdDocenteDTO());
        return corsoDTO;
    }
    public static CorsoDTO entityToDTO (Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setId(corso.getId());
        corsoDTO.setNomeDocenteDTO(corso.getDocenteCognome());
        corsoDTO.setCognomeDocenteDTO(corso.getDocenteNome());
        corsoDTO.setIdDocenteDTO(corso.getDocenteId());
        if(!corso.getDiscenti().isEmpty()){
            for (int i=0; i<corso.getDiscenti().size(); i++){
                DiscenteDTO discenteDTO= DiscenteConverter.entityToDTO(corso.getDiscenti().get(i));
                corsoDTO.getDiscenti().add(discenteDTO);
            }
        }

        return corsoDTO;
    }

    public static CorsoDTO entityToDTOAddDiscente (Corso corso){
        CorsoDTO corsoDTO = new CorsoDTO();
        corsoDTO.setNomeCorso(corso.getNomeCorso());
        corsoDTO.setDataInizio(corso.getDataInizio());
        corsoDTO.setDurata(corso.getDurata());
        corsoDTO.setId(corso.getId());
        corsoDTO.setNomeDocenteDTO(corso.getDocenteCognome());
        corsoDTO.setCognomeDocenteDTO(corso.getDocenteNome());
        corsoDTO.setIdDocenteDTO(corso.getDocenteId());
        ArrayList<DiscenteDTO> discentiDTO = new ArrayList<>();
        corsoDTO.setDiscenti(discentiDTO);
        if(!corso.getDiscenti().isEmpty()) {
            for (Discente d : corso.getDiscenti()) {
                DiscenteDTO discenteDTO = DiscenteConverter.entityToDTO(d);
                corsoDTO.getDiscenti().add(discenteDTO);
            }
        }
        return corsoDTO;
    }


    public static Corso DTOToEntity(CorsoDTO corsoDTO){
        Corso corso = new Corso();
        corso.setDataInizio(corsoDTO.getDataInizio());
        corso.setDurata(corsoDTO.getDurata());
        corso.setNomeCorso(corsoDTO.getNomeCorso());

        return corso;
    }

    public static Corso DTOToEntityUpdate(CorsoDTO corsoDTO, Corso corso){
        if(corsoDTO.getNomeCorso().isEmpty() && corsoDTO.getNomeCorso() != null){
            corso.setNomeCorso(corsoDTO.getNomeCorso());
        }
        if (corsoDTO.getDurata().isEmpty() && corsoDTO.getDurata() != null){
            corso.setDurata(corsoDTO.getDurata());
        }
        if (corsoDTO.getDataInizio() != null){
            corso.setDataInizio(corsoDTO.getDataInizio());
        }

        return corso;
    }
}
