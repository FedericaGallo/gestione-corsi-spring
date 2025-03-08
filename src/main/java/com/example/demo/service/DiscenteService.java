package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscenteConverter;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {
    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;

    public DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
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

    public DiscenteDTO updateDiscente(Integer id, DiscenteDTO discenteDTO) {
        Optional<Discente> discente = discenteRepository.findById(id);
        if (discente.isPresent()) {
            Discente existingDiscente = discente.get();
            existingDiscente.setNome(discenteDTO.getNome());
            existingDiscente.setCognome(discenteDTO.getCognome());
            existingDiscente.setMatricola(discenteDTO.getMatricola());
            existingDiscente.setDataDiNascita(discenteDTO.getDataDiNascita());
            Discente updatedDiscente = discenteRepository.save(existingDiscente);
            return DiscenteConverter.entityToDTOGetDiscente(updatedDiscente);
        } else {
            throw new EntityNotFoundException("Discente not found with id " + id);
        }
    }

    @Transactional
    public DiscenteDTO iscriviDiscente(Integer id, DiscenteDTO discenteDTO) {
        Optional<Discente> discenteOpt = discenteRepository.findById(id);
        if (discenteOpt.isPresent()) {
            Discente existingDiscente = discenteOpt.get();
            System.out.println("Corsi seguiti prima dell'aggiornamento: " + existingDiscente.getCorsiSeguiti());

            // Itera sui corsi ricevuti
            for (Integer corsoId : discenteDTO.getCorsiSeguitiId()) {
                // Recupera il Corso dal database
                Corso corsoNuovo = corsoRepository.findById(corsoId)
                        .orElseThrow(() -> new RuntimeException("Corso non trovato"));

                // Aggiungi il Corso alla lista dei corsi del Discente (se non è già presente)
                if (!existingDiscente.getCorsiSeguiti().contains(corsoNuovo)) {
                    existingDiscente.getCorsiSeguiti().add(corsoNuovo);
                }

                // Aggiungi il Discente alla lista dei discenti del Corso (se non è già presente)
                if (!corsoNuovo.getDiscenti().contains(existingDiscente)) {
                    corsoNuovo.getDiscenti().add(existingDiscente);
                }

                // Salva il Corso per aggiornare la relazione
                corsoRepository.save(corsoNuovo);
            }

            // Salva il Discente con i nuovi corsi
            Discente updatedDiscente = discenteRepository.save(existingDiscente);

            System.out.println("Corsi seguiti dopo l'aggiornamento: " + updatedDiscente.getCorsiSeguiti());

            // Converti e restituisci il DTO del Discente aggiornato
            return DiscenteConverter.entityToDTOGetDiscente(updatedDiscente);
        } else {
            throw new EntityNotFoundException("Discente non trovato con id " + id);
        }
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

    public Page<DiscenteDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("nome").ascending());
        Page<Discente> discenti = discenteRepository.findAll(pageable);
        List<DiscenteDTO> discentiDTOs = new ArrayList<>();
        for (Discente discente : discenti) {
            DiscenteDTO discenteDTO = DiscenteConverter.entityToDTOGetDiscente(discente);
            discentiDTOs.add(discenteDTO);
        }
        return new PageImpl<>(discentiDTOs, PageRequest.of(discenti.getNumber(), discenti.getSize(), discenti.getSort()), discenti.getTotalElements());
    }

    public List<CorsoDTO> getCorsiDisponibili(int id) {
        List<Corso> corsiDisponibili = corsoRepository.findCorsiDisponibiliByDiscenteId(id);
        List<CorsoDTO> corsiDTODisponibili = new ArrayList<>();
        for (Corso corso : corsiDisponibili){
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso);
            corsiDTODisponibili.add(corsoDTO);
        }
        return corsiDTODisponibili;
    }
}
