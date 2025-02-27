package com.example.demo.service;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteProva;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.entity.Docente;
import com.example.demo.utils.DocenteConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {
    //Spring fa un'istanza di DocenteService
    private final DocenteRepository docenteRepository;

    public DocenteService(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    public DocenteDTO getDocenteById(Integer id) {

        Optional<Docente> docente = docenteRepository.findById(id);
        //Docente docente = docenteRepository.findById(id).get();
        // Se trova il docente converte l'entity in DTO e lo ritorna
        if (docente.isPresent()) {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTOGetDocente(docente.get());
            return docenteDTO;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public DocenteDTO addDocente(DocenteDTO docenteDTO) {

        Docente docente = new Docente();
        docente.setNome(docenteDTO.getNome());
        docente.setCognome(docenteDTO.getCognome());
        docente.setDescrizione(docenteDTO.getDescrizione());
        Docente savedDocente = docenteRepository.save(docente);
        DocenteDTO savedDocenteDTO = DocenteConverter.entityToDTO(savedDocente);

        return savedDocenteDTO;
    }

    /*public List<DocenteDTO> findAll() {
        List<Docente> docenti = docenteRepository.findAll();
        List<DocenteDTO> docenteDTOs = new ArrayList<>();
        for (Docente docente : docenti) {
            DocenteDTO docenteDTO = DocenteConverter.entityToDTOGetDocente(docente);
            docenteDTOs.add(docenteDTO);
        }
        return docenteDTOs;
    }*/

    public Page<DocenteProva> findAllProva(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("nome").ascending());
        Page<Docente> docenti = docenteRepository.findAll(pageable);
        List<DocenteProva> docentiDTOs = new ArrayList<>();
        for (Docente docente : docenti) {
            DocenteProva docenteDTO = DocenteConverter.entityToDTOprova(docente);
            docentiDTOs.add(docenteDTO);
        }
        return new PageImpl<>(docentiDTOs, PageRequest.of(docenti.getNumber(), docenti.getSize(), docenti.getSort()), docenti.getTotalElements());

    }


    public DocenteDTO updateDocente(Integer id, DocenteDTO docenteDTO) {
        Optional<Docente> docente = docenteRepository.findById(id);
        if (docente.isPresent()) {
            Docente existingDocente = docente.get();
            existingDocente.setNome(docenteDTO.getNome());
            existingDocente.setCognome(docenteDTO.getCognome());
            existingDocente.setDescrizione(docenteDTO.getDescrizione());
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

    public Page<Docente> findAllByNome(String nome) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("nome").ascending());
        Page<Docente> docenti = docenteRepository.findAllByNome(nome, pageable);
        return docenti;
    }
}
