package com.example.demo.controller;

import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import com.example.demo.service.DocenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //gestisce richieste REST
@RequestMapping("/docente") //tutte le richieste che cominciano con /docente
public class DocenteController {
    private final DocenteService docenteService;
    //iniezione dipendenze tramite costruttore
    public DocenteController(DocenteService docenteService){
        this.docenteService= docenteService;
    }

    @GetMapping("/getDocenteById/{idDocente}")
    public DocenteDTO getDocenteById(@PathVariable("idDocente")Integer id){
          return docenteService.getDocenteById(id);
    }
    //Mappa le richieste HTTP POST all’endpoint /addDocente.
    @PostMapping("/addDocente")
    public ResponseEntity<DocenteDTO> addDocente(@RequestBody DocenteDTO docenteDTO) {
        DocenteDTO savedDocente = docenteService.addDocente(docenteDTO);
        return ResponseEntity.ok(savedDocente);
    }
    //ResponseEntity è una classe di Spring che rappresenta l’intera risposta HTTP, inclusi codice di stato, intestazioni e corpo.
    @DeleteMapping("/deleteDocente/{idDocente}")
    public ResponseEntity<Void> deleteDocente(@PathVariable("idDocente") Integer id) {
        docenteService.deleteDocente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateDocente/{idDocente}")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable("idDocente") Integer id, @RequestBody DocenteDTO docenteDTO) {
        DocenteDTO updatedDocente = docenteService.updateDocente(id, docenteDTO);
        return ResponseEntity.ok(updatedDocente);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<DocenteDTO>> findAll() {
        List<DocenteDTO> docenti = docenteService.findAll();
        return ResponseEntity.ok(docenti);
    }

}
