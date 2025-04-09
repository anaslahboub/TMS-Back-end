package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.contient.ContientDTO;
import com.izorai.pfa.module2.services.contient.ContientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contients")
public class ContientController {

    private final ContientService contientService;

    public ContientController(ContientService contientService) {
        this.contientService = contientService;
    }

    @PostMapping
    public ResponseEntity<ContientDTO> createContient(@RequestBody ContientDTO contientDTO) {
        ContientDTO newContient = contientService.createContient(contientDTO);
        return new ResponseEntity<>(newContient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContientDTO>> getAllContients() {
        List<ContientDTO> contients = contientService.getAllContients();
        return new ResponseEntity<>(contients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContientDTO> getContientById(@PathVariable Long id) {
        Optional<ContientDTO> contientDTO = contientService.getContientById(id);
        return contientDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/marchandise/{marchandiseId}")
    public ResponseEntity<List<ContientDTO>> getContientsByMarchandise(@PathVariable Long marchandiseId) {
        List<ContientDTO> contients = contientService.getContientsByMarchandise(marchandiseId);
        return new ResponseEntity<>(contients, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContientDTO> updateContient(
            @PathVariable Long id,
            @RequestBody ContientDTO contientDTO) {
        ContientDTO updatedContient = contientService.updateContient(id, contientDTO);
        return new ResponseEntity<>(updatedContient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContient(@PathVariable Long id) {
        contientService.deleteContient(id);
        return ResponseEntity.noContent().build();
    }
}