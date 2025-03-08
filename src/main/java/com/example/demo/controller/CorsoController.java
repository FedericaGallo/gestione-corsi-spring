package com.example.demo.controller;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.DTO.DocenteProva;
import com.example.demo.entity.Corso;
import com.example.demo.service.CorsoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/corso")
public class CorsoController {
    private final CorsoService corsoService;

    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @PostMapping("/addCorso")
    public ResponseEntity<CorsoDTO> addCorso(@RequestBody CorsoDTO corsoDTO) {
        CorsoDTO savedCorso = corsoService.addCorso(corsoDTO);
        return ResponseEntity.ok(savedCorso);
    }

    @GetMapping("/getCorsoById/{idCorso}")
    public CorsoDTO getCorsoByID(@PathVariable("idCorso") Integer id) {
        return corsoService.getCorsoById(id);
    }

    @DeleteMapping("/deleteCorso/{idCorso}")
    public ResponseEntity<Void> deleteCorso(@PathVariable("idCorso") Integer id) {
        corsoService.deleteCorso(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateCorso/{idCorso}")
    public ResponseEntity<CorsoDTO> updateCorso(@PathVariable("idCorso") Integer id, @RequestBody CorsoDTO corsoDTO) {
        CorsoDTO updatedCorso = corsoService.updateCorso(id, corsoDTO);
        return ResponseEntity.ok(updatedCorso);
    }

    @PutMapping("/addDiscente/{idCorso}")
    public ResponseEntity<CorsoDTO> addDiscente(@PathVariable("idCorso") Integer id, @RequestBody CorsoDTO corsoDTO) {
        CorsoDTO updatedCorso = corsoService.addDiscente(id, corsoDTO);
        return ResponseEntity.ok(updatedCorso);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/removeDiscente/{idCorso}")
    public ResponseEntity<CorsoDTO> removeDiscente(@PathVariable("idCorso") Integer idCorso, @RequestBody DiscenteDTO discenteDTO) {
        CorsoDTO updatedCorso = corsoService.removeDiscente(discenteDTO.getId(), idCorso);
        return ResponseEntity.ok(updatedCorso);
    }

    //   @GetMapping("/findAll")
//    public ResponseEntity<List<CorsoDTO>> findAll() {
//        List<CorsoDTO> corsi = corsoService.findAll();
//        return ResponseEntity.ok(corsi);
//    }
    @GetMapping("/findAll")
    public ResponseEntity<Page<CorsoDTO>> findAll(@RequestParam(defaultValue = "0") int page) {
        Page<CorsoDTO> corsi = corsoService.findAll(page);
        return ResponseEntity.ok(corsi);
    }

    @GetMapping("/{corsoId}/discenti-disponibili")
    public ResponseEntity<List<DiscenteDTO>> getDiscentiDisponibili(@PathVariable Integer corsoId) {
        List<DiscenteDTO> discentiDisponibiliDTO = corsoService.getDiscentiDisponibili(corsoId);
        return ResponseEntity.ok(discentiDisponibiliDTO);
    }
    @PutMapping("{idCorso}/iscrivi")
    public ResponseEntity<CorsoDTO> iscrivialCorso(@PathVariable("idCorso") Integer id, @RequestBody CorsoDTO corsoDTO) {
        CorsoDTO updatedCorso = corsoService.iscriviAlCorso(id, corsoDTO);
        return ResponseEntity.ok(updatedCorso);
    }
}
