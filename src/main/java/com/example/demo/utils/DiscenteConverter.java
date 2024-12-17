package com.example.demo.utils;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;

import java.util.ArrayList;
import java.util.List;

//import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

public class DiscenteConverter {
    public static DiscenteDTO entityToDTO (Discente discente){
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataDiNascita(discente.getDataDiNascita());
        //discenteDTO.setCorsiSeguiti(null);
        return discenteDTO;
    }
    public static DiscenteDTO entityToDTO2 (Discente discente){
        DiscenteDTO discenteDTO = new DiscenteDTO();
        discenteDTO.setId(discente.getId());
        discenteDTO.setNome(discente.getNome());
        discenteDTO.setCognome(discente.getCognome());
        discenteDTO.setMatricola(discente.getMatricola());
        discenteDTO.setDataDiNascita(discente.getDataDiNascita());
        /*List<Corso> corsiSeguiti = discente.getCorsiSeguiti();
        List<CorsoDTO> corsiSeguitiDTO= new ArrayList<>();
        for (int i=0; i< corsiSeguiti.size(); i++){
            CorsoDTO corso= CorsoConverter.entityToDTO(corsiSeguiti.get(i));
            corsiSeguitiDTO.add(corso);
        }
        discenteDTO.setCorsiSeguiti(corsiSeguitiDTO);*/

        return discenteDTO;
    }
    public static Discente DTOToEntity (DiscenteDTO discenteDTO){
        Discente discente = new Discente();
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setMatricola(discenteDTO.getMatricola());
        discente.setDataDiNascita(discenteDTO.getDataDiNascita());
        List<CorsoDTO> corsiSeguiti = discenteDTO.getCorsiSeguiti();
        List<Corso> corsiSeguitiEntity= new ArrayList<>();
        for (int i=0; i< corsiSeguiti.size(); i++){
        Corso corso= CorsoConverter.DTOToEntity(corsiSeguiti.get(i));
         corsiSeguitiEntity.add(corso);
        }
        discente.setCorsiSeguiti(corsiSeguitiEntity);
        return discente;
    }
    public static Discente DTOToEntity2 (DiscenteDTO discenteDTO){
        Discente discente = new Discente();
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setMatricola(discenteDTO.getMatricola());
        discente.setDataDiNascita(discenteDTO.getDataDiNascita());
        /*List<CorsoDTO> corsiSeguiti = discenteDTO.getCorsiSeguiti();
        List<Corso> corsiSeguitiEntity= new ArrayList<>();
        for (int i=0; i< corsiSeguiti.size(); i++){
            Corso corso= new Corso();
            corso.setId(corsiSeguiti.get(i).getId());
            corsiSeguitiEntity.add(corso);
        }
        discente.setCorsiSeguiti(corsiSeguitiEntity);*/
        return discente;
    }
}
