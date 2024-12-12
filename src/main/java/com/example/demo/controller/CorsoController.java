package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.service.CorsoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corso")
public class CorsoController {
    private final CorsoService corsoService;
    public CorsoController(CorsoService corsoService){
        this.corsoService=corsoService;
    }
    @PostMapping("/addCorso")
    public ResponseEntity<CorsoDTO> addCorso(@RequestBody CorsoDTO corsoDTO) {
        CorsoDTO savedCorso = corsoService.addCorso(corsoDTO);
        return ResponseEntity.ok(savedCorso);
    }
    @GetMapping("/getCorsoById/{idCorso}")
    public CorsoDTO getCorsoByID(@PathVariable("idCorso")Integer id){return corsoService.getCorsoById(id); }
}
