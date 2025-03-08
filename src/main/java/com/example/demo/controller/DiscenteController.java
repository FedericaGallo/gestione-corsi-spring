package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    @GetMapping("/getDiscenteById/{id}")
    public ResponseEntity<DiscenteDTO> getDiscente(@PathVariable("id") Integer id){
        DiscenteDTO discente = discenteService.getDiscenteById(id);
        return ResponseEntity.ok(discente);
    }
    @DeleteMapping("/deleteDiscente/{id}")
    public ResponseEntity<Void> deleteDiscente(@PathVariable("id") Integer id){
        discenteService.deleteDiscente(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/findAllPagination")
    public ResponseEntity<Page<DiscenteDTO>> findAll(@RequestParam(defaultValue = "0") int page) {
        Page<DiscenteDTO> discenti = discenteService.findAll(page);
        return ResponseEntity.ok(discenti);
    }
    @PutMapping("/updateDiscente/{idDiscente}")
    public ResponseEntity<DiscenteDTO> updateDiscente(@PathVariable("idDiscente") Integer id, @RequestBody DiscenteDTO discenteDTO) {
        DiscenteDTO updatedDiscente = discenteService.updateDiscente(id, discenteDTO);
        return ResponseEntity.ok(updatedDiscente);
    }
    @PutMapping("{idDiscente}/iscrivi")
    public ResponseEntity<DiscenteDTO> iscriviDiscente(@PathVariable("idDiscente") Integer id, @RequestBody DiscenteDTO discenteDTO) {
        DiscenteDTO updatedDiscente = discenteService.iscriviDiscente(id, discenteDTO);
        return ResponseEntity.ok(updatedDiscente);
    }

    @GetMapping("/{discenteId}/corsi-disponibili")
    public ResponseEntity<List<CorsoDTO>> getCorsiDisponibili(@PathVariable Integer discenteId) {
        List<CorsoDTO> corsiDisponibiliDTO = discenteService.getCorsiDisponibili(discenteId);
        return ResponseEntity.ok(corsiDisponibiliDTO);
    }
}
