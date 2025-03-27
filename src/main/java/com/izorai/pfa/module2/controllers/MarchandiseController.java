package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.marchandises.MarchandiseDTO;
import com.izorai.pfa.module2.services.marchandises.MarchandiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marchandises")
public class MarchandiseController {

    private final MarchandiseService marchandiseService;

    public MarchandiseController(MarchandiseService marchandiseService) {
        this.marchandiseService = marchandiseService;
    }

    @PostMapping
    public ResponseEntity<MarchandiseDTO> createMarchandise(@RequestBody MarchandiseDTO marchandiseDTO) {
        MarchandiseDTO newMarchandise = marchandiseService.createMarchandise(marchandiseDTO);
        return new ResponseEntity<>(newMarchandise, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarchandiseDTO>> getAllMarchandises() {
        List<MarchandiseDTO> marchandises = marchandiseService.getAllMarchandises();
        return new ResponseEntity<>(marchandises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarchandiseDTO> getMarchandiseById(@PathVariable Long id) {
        Optional<MarchandiseDTO> marchandiseDTO = marchandiseService.getMarchandiseById(id);
        return marchandiseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<MarchandiseDTO> getMarchandiseByCode(@PathVariable String code) {
        Optional<MarchandiseDTO> marchandiseDTO = marchandiseService.getMarchandiseByCode(code);
        return marchandiseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarchandiseDTO> updateMarchandise(
            @PathVariable Long id,
            @RequestBody MarchandiseDTO marchandiseDTO) {
        MarchandiseDTO updatedMarchandise = marchandiseService.updateMarchandise(id, marchandiseDTO);
        return new ResponseEntity<>(updatedMarchandise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarchandise(@PathVariable Long id) {
        marchandiseService.deleteMarchandise(id);
        return ResponseEntity.noContent().build();
    }
}