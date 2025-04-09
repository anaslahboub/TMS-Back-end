package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.marchandises.EmballageDTO;
import com.izorai.pfa.module2.services.Emballage.EmballageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emballages")
public class EmballageController {

    private final EmballageService emballageService;

    public EmballageController(EmballageService emballageService) {
        this.emballageService = emballageService;
    }

    @PostMapping
    public ResponseEntity<EmballageDTO> createEmballage(@RequestBody EmballageDTO emballageDTO) {
        EmballageDTO newEmballage = emballageService.createEmballage(emballageDTO);
        return new ResponseEntity<>(newEmballage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmballageDTO>> getAllEmballages() {
        List<EmballageDTO> emballages = emballageService.getAllEmballages();
        return new ResponseEntity<>(emballages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmballageDTO> getEmballageById(@PathVariable Long id) {
        Optional<EmballageDTO> emballageDTO = emballageService.getEmballageById(id);
        return emballageDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<EmballageDTO> getEmballageByNom(@PathVariable String nom) {
        Optional<EmballageDTO> emballageDTO = emballageService.getEmballageByNom(nom);
        return emballageDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmballageDTO> updateEmballage(
            @PathVariable Long id,
            @RequestBody EmballageDTO emballageDTO) {
        EmballageDTO updatedEmballage = emballageService.updateEmballage(id, emballageDTO);
        return new ResponseEntity<>(updatedEmballage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmballage(@PathVariable Long id) {
        emballageService.deleteEmballage(id);
        return ResponseEntity.noContent().build();
    }
}