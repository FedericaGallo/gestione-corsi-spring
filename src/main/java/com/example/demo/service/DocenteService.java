package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.entity.Docente;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    //Spring fa un'istanza di DocenteService
    private final DocenteRepository docenteRepository;
    public DocenteService( DocenteRepository docenteRepository){
        this.docenteRepository=docenteRepository;
    }

     public DocenteDTO getDocenteById(Integer id) {

        Optional<Docente> docente = docenteRepository.findById(id);
        //Docente docente = docenteRepository.findById(id).get();
         // Se trova il docente converte l'entity in DTO e lo ritorna
        if (docente.isPresent()){
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente.get());
            return docenteDTO;
        }else {
            throw new EntityNotFoundException();
        }
    }

    public DocenteDTO addDocente(DocenteDTO docenteDTO) {

        Docente docente = new Docente();
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        Docente savedDocente = docenteRepository.save(docente);

        DocenteDTO savedDocenteDTO = DocenteConverter.entityToDTO(docente);

        return savedDocenteDTO;
    }

    public List<DocenteDTO> findAll() {
        List<Docente> docenti = docenteRepository.findAll();
        List<DocenteDTO> docenteDTOs = new ArrayList<>();
        for (Docente docente : docenti) {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTO(docente);
            docenteDTOs.add(docenteDTO);
        }
        return docenteDTOs;
    }

    public DocenteDTO updateDocente(Integer id, DocenteDTO docenteDTO) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            Docente existingDocente = docente.get();
            existingDocente.setNome(docenteDTO.getNome());
            existingDocente.setCognome(docenteDTO.getCognome());
            Docente updatedDocente = docenteRepository.save(existingDocente);
            return DocenteConverter.entityToDTO(updatedDocente);
        } else {
            throw new EntityNotFoundException("Docente not found with id " + id);
        }
    }


    public void deleteDocente(Integer id) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            docenteRepository.delete(docente.get());
        } else {
            throw new EntityNotFoundException("Docente not found with id " + id);
        }
    }

}
