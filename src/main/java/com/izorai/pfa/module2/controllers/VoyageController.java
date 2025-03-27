package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.services.VoyageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {

    private final VoyageService voyageService;

    public VoyageController(VoyageService voyageService) {
        this.voyageService = voyageService;
    }

    @PostMapping
    public ResponseEntity<VoyageDTO> createVoyage(@RequestBody VoyageDTO voyageDTO) {
        VoyageDTO newVoyage = voyageService.createVoyage(voyageDTO);
        return new ResponseEntity<>(newVoyage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VoyageDTO>> getAllVoyages() {
        List<VoyageDTO> voyages = voyageService.getAllVoyages();
        return new ResponseEntity<>(voyages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoyageDTO> getVoyageById(@PathVariable Long id) {
        Optional<VoyageDTO> voyageDTO = voyageService.getVoyageById(id);
        return voyageDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/periode")
    public ResponseEntity<List<VoyageDTO>> getVoyagesByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<VoyageDTO> voyages = voyageService.getVoyagesByDateRange(start, end);
        return new ResponseEntity<>(voyages, HttpStatus.OK);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<VoyageDTO>> getVoyagesByStatut(@PathVariable String statut) {
        List<VoyageDTO> voyages = voyageService.getVoyagesByStatut(statut);
        return new ResponseEntity<>(voyages, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoyageDTO> updateVoyage(
            @PathVariable Long id,
            @RequestBody VoyageDTO voyageDTO) {
        VoyageDTO updatedVoyage = voyageService.updateVoyage(id, voyageDTO);
        return new ResponseEntity<>(updatedVoyage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
        return ResponseEntity.noContent().build();
    }
}