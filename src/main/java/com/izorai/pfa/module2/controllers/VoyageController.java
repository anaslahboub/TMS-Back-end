package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import com.izorai.pfa.module2.services.VoyageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
            @RequestParam String start,
            @RequestParam String end) {
        List<VoyageDTO> voyages = voyageService.getVoyagesByDateRange(start, end);
        return new ResponseEntity<>(voyages, HttpStatus.OK);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<VoyageDTO>> getVoyagesByStatut(@PathVariable EtatVoyage statut) {
        List<VoyageDTO> voyages = voyageService.getVoyagesByStatus(statut);
        return new ResponseEntity<>(voyages, HttpStatus.OK);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<VoyageDTO> updateVoyageStatus(
            @PathVariable Long id,
            @RequestParam EtatVoyage newStatus) {
        VoyageDTO updatedVoyage = voyageService.updateStatus(id, newStatus);
        return new ResponseEntity<>(updatedVoyage, HttpStatus.OK);
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

    @PutMapping("/{voyageId}/chauffeur/{chauffeurId}")
    public ResponseEntity<VoyageDTO> assignChauffeur(
            @PathVariable Long voyageId,
            @PathVariable Long chauffeurId) {
        VoyageDTO updatedVoyage = voyageService.assignChauffeur(voyageId, chauffeurId);
        return new ResponseEntity<>(updatedVoyage, HttpStatus.OK);
    }

    @PutMapping("/{voyageId}/camion/{camionId}")
    public ResponseEntity<VoyageDTO> assignCamion(
            @PathVariable Long voyageId,
            @PathVariable Long camionId) {
        VoyageDTO updatedVoyage = voyageService.assignCamion(voyageId, camionId);
        return new ResponseEntity<>(updatedVoyage, HttpStatus.OK);
    }

    @PutMapping("/{voyageId}/remorque/{remorqueId}")
    public ResponseEntity<VoyageDTO> assignRemorque(
            @PathVariable Long voyageId,
            @PathVariable Long remorqueId) {
        VoyageDTO updatedVoyage = voyageService.assignRemorque(voyageId, remorqueId);
        return new ResponseEntity<>(updatedVoyage, HttpStatus.OK);
    }
}