package com.example.demo.service;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.utils.CorsoConverter;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {
    private final CorsoRepository corsoRepository;
    public CorsoService( CorsoRepository corsoRepository){
        this.corsoRepository=corsoRepository;
    }
    public CorsoDTO addCorso(CorsoDTO corsoDTO) {

        Corso corso = new Corso();
        corso.setDurata(corsoDTO.getDurata());
        corso.setNomeCorso(corsoDTO.getNomeCorso());
        corso.setDataInizio(corsoDTO.getDataInizio());
        //corso.setDocenteId(corsoDTO.getDocenteDTOId());
        Corso savedCorso = corsoRepository.save(corso);

        CorsoDTO savedCorsoDTO = CorsoConverter.entityToDTO2(savedCorso);

        return savedCorsoDTO;
    }
    public CorsoDTO getCorsoById(Integer id){
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()){
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso.get());
            //System.out.println("CorsoDTO ID: " + corsoDTO.getDocenteDTOId());
            return corsoDTO;
        }else {
            throw new EntityNotFoundException();
        }
    }

    public void deleteCorso(Integer id){
        Optional<Corso> corso = corsoRepository.findById(id);
        if(corso.isPresent()){
            corsoRepository.delete(corso.get());
        }else{
            throw new EntityNotFoundException("Corso not found with id " + id);
        }
    }

    public CorsoDTO updateCorso(Integer id, CorsoDTO corsoDTO){
        Optional<Corso> corso = corsoRepository.findById(id);
        if (corso.isPresent()){
           Corso existingCorso = corso.get();
           existingCorso.setNomeCorso(corsoDTO.getNomeCorso());
           existingCorso.setDurata(corsoDTO.getDurata());
           //existingCorso.setDiscenti(corsoDTO.getDiscenti());
           Docente docente =new Docente();
           //docente.setId(corsoDTO.getDocenteDTOId());
           existingCorso.setDocente(docente);
           Corso updateCorso = corsoRepository.save(existingCorso);
           return CorsoConverter.entityToDTO(updateCorso);
        }else{
            throw new EntityNotFoundException("Corso not found");
        }
    }

    public List<CorsoDTO> findAll(){
        List<Corso>corsi=corsoRepository.findAll();
        List<CorsoDTO>corsiDTO=new ArrayList<>();
        for(Corso corso : corsi){
            CorsoDTO corsoDTO = CorsoConverter.entityToDTO(corso);
            corsiDTO.add(corsoDTO);
        }
        return corsiDTO;
    }
}
