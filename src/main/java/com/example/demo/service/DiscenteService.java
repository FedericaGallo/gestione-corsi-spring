package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {
    private final DiscenteRepository discenteRepository;

    public DiscenteService(DiscenteRepository discenteRepository) {
        this.discenteRepository = discenteRepository;
    }

    public DiscenteDTO addDiscente(DiscenteDTO discenteDTO) {
        Discente discente = DiscenteConverter.DTOToEntity(discenteDTO);

        Discente savedDiscente = discenteRepository.save(discente);
        DiscenteDTO discenteDTOSaved = DiscenteConverter.entityToDTOGetDiscente(savedDiscente);
        return discenteDTOSaved;

    }

    public DiscenteDTO getDiscenteById(Integer id) {
        Discente discente = discenteRepository.getById(id);
        return DiscenteConverter.entityToDTOGetDiscente(discente);
    }

    public void deleteDiscente(Integer id) {
        Optional<Discente> discente = discenteRepository.findById(id);
        if (discente.isPresent()) {
            discente.get().getCorsiSeguiti().clear();
            discenteRepository.save(discente.get());
            discenteRepository.delete(discente.get());
        } else {
            throw new EntityNotFoundException("Discente not found with id" + id);
        }
    }
}
