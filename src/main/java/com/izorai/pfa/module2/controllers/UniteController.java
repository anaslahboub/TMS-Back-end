package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.marchandises.UniteDTO;
import com.izorai.pfa.module2.services.marchandises.UniteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/unites")
public class UniteController {

    private final UniteService uniteService;

    public UniteController(UniteService uniteService) {
        this.uniteService = uniteService;
    }

    @PostMapping
    public ResponseEntity<UniteDTO> createUnite(@RequestBody UniteDTO uniteDTO) {
        UniteDTO newUnite = uniteService.createUnite(uniteDTO);
        return new ResponseEntity<>(newUnite, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UniteDTO>> getAllUnites() {
        List<UniteDTO> unites = uniteService.getAllUnites();
        return new ResponseEntity<>(unites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniteDTO> getUniteById(@PathVariable Long id) {
        Optional<UniteDTO> uniteDTO = uniteService.getUniteById(id);
        return uniteDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<UniteDTO> getUniteByNom(@PathVariable String nom) {
        Optional<UniteDTO> uniteDTO = uniteService.getUniteByNom(nom);
        return uniteDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniteDTO> updateUnite(
            @PathVariable Long id,
            @RequestBody UniteDTO uniteDTO) {
        UniteDTO updatedUnite = uniteService.updateUnite(id, uniteDTO);
        return new ResponseEntity<>(updatedUnite, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnite(@PathVariable Long id) {
        uniteService.deleteUnite(id);
        return ResponseEntity.noContent().build();
    }
}