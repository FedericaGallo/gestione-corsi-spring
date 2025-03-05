package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteProva;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Discente;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DiscenteConverter;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;
    private final DocenteRepository docenteRepository;
    private final DiscenteRepository discenteRepository;
    public CorsoService( CorsoRepository corsoRepository, DocenteRepository docenteRepository, DiscenteRepository discenteRepository){
        this.corsoRepository=corsoRepository;
        this.docenteRepository = docenteRepository;
        this.discenteRepository = discenteRepository;
    }
    public CorsoDTO addCorso(CorsoDTO corsoDTO) {

        Corso corso = CorsoConverter.DTOToEntity(corsoDTO);
        Docente docente = docenteRepository.findById(corsoDTO.getIdDocenteDTO()).orElseThrow(() -> new RuntimeException("docente not fount"));
        corso.setDocente(docente);
        Corso savedCorso = corsoRepository.save(corso);
        CorsoDTO savedCorsoDTO = getCorsoById(savedCorso.getId());
        return savedCorsoDTO;

    }
    public CorsoDTO getCorsoById(Integer id){
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()){
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso.get());
            return corsoDTO;
        }else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteCorso(Integer id){
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()){
            corso.get().getDiscenti().clear();
            corsoRepository.save(corso.get());
            corsoRepository.delete(corso.get());
        }else{
            throw new EntityNotFoundException("Corso not found with id " + id);
        }
    }

    public CorsoDTO updateCorso(Integer id, CorsoDTO corsoDTO){
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()){
           Corso existingCorso = corso.get();
           Corso corsoToSave = CorsoConverter.DTOToEntityUpdate(corsoDTO, existingCorso);
           Docente docente = docenteRepository.findById(corsoDTO.getIdDocenteDTO()).orElseThrow(()-> new RuntimeException("docente not found"));
           existingCorso.setDocente(docente);
           Corso updateCorso = corsoRepository.save(existingCorso);
           return CorsoConverter.entityToDTO(updateCorso);
        }else{
            throw new EntityNotFoundException("Corso not found");
        }
    }

    public CorsoDTO addDiscente(Integer id, CorsoDTO corsoDTO){
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()){
            Corso existingCorso = corso.get();
            //salva i discentiDTO inviati dal client
            List<DiscenteDTO> discentiDTO= corsoDTO.getDiscenti();
            //salvo i discenti già iscritti al corso
            List<Discente> discenti= existingCorso.getDiscenti();
            //converto discentiDTO in discenti assegnando id e aggiungo a discenti
            for (DiscenteDTO d : discentiDTO){
                Discente discente = discenteRepository.findById(d.getId()).orElseThrow(()-> new RuntimeException("discente not found"));
                discenti.add(discente);
            }

            existingCorso.setDiscenti(discenti);
            Corso updateCorso = corsoRepository.save(existingCorso);
            CorsoDTO savedCorsoDTO = CorsoConverter.entityToDTO(updateCorso);
            return savedCorsoDTO;

        }else{
            throw new EntityNotFoundException("Corso not found");
        }
    }
  /*  public List<CorsoDTO> findAll(){
        List<Corso>corsi=corsoRepository.findAll();
        List<CorsoDTO>corsiDTO=new ArrayList<>();
        for(Corso corso : corsi){
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso);
            corsiDTO.add(corsoDTO);
        }
        return corsiDTO;
    }*/

    public Page<CorsoDTO> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("nomeCorso").ascending());
        Page<Corso> corsi = corsoRepository.findAll(pageable);
        List<CorsoDTO> corsiDTOs = new ArrayList<>();
        for (Corso corso : corsi) {
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso);
            corsiDTOs.add(corsoDTO);
        }
        return new PageImpl<>(corsiDTOs, PageRequest.of(corsi.getNumber(), corsi.getSize(), corsi.getSort()), corsi.getTotalElements());
    }
}
