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
        Discente discente= DiscenteConverter.DTOToEntity2(discenteDTO);

        Discente savedDiscente = discenteRepository.save(discente);
        return DiscenteConverter.entityToDTO2(savedDiscente);

    }
}
