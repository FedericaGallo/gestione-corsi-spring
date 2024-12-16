package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discente")
public class DiscenteController {
    private final DiscenteService discenteService;
    public DiscenteController(DiscenteService discenteService){
        this.discenteService = discenteService;
    }
    @PostMapping("/addDiscente")
    public ResponseEntity<DiscenteDTO> addDiscente(@RequestBody DiscenteDTO discenteDTO) {
        DiscenteDTO savedDiscente = discenteService.addDiscente(discenteDTO);
        return ResponseEntity.ok(savedDiscente);
    }
}
