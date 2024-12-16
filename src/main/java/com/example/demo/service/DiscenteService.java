package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscenteConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscenteService {
    private final DiscenteRepository discenteRepository;
    public DiscenteService(DiscenteRepository discenteRepository){
        this.discenteRepository=discenteRepository;
    }
    public DiscenteDTO addDiscente(DiscenteDTO discenteDTO){
        Discente discente= new Discente();
        discente.setNome(discenteDTO.getNome());
        discente.setCognome(discenteDTO.getCognome());
        discente.setMatricola(discenteDTO.getMatricola());
        discente.setDataDiNascita(discenteDTO.getDataDiNascita());

        Discente savedDiscente = discenteRepository.save(discente);
        DiscenteDTO discenteDTOSaved= new DiscenteDTO();
        discenteDTOSaved.setNome(savedDiscente.getNome());
        discenteDTOSaved.setCognome(savedDiscente.getCognome());
        discenteDTOSaved.setMatricola(savedDiscente.getMatricola());
        discenteDTOSaved.setDataDiNascita(savedDiscente.getDataDiNascita());
        discenteDTOSaved.setId(savedDiscente.getId());
        return discenteDTOSaved;

    }
}
