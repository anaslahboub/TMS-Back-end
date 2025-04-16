package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageEtatDTO;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import com.izorai.pfa.module2.mappers.VoyageMapper;
import com.izorai.pfa.module2.services.VoyageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {

    private final VoyageService voyageService;
    private final VoyageMapper voyageMapper;

    public VoyageController(VoyageService voyageService, VoyageMapper voyageMapper) {
        this.voyageService = voyageService;
        this.voyageMapper = voyageMapper;
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

    @GetMapping("/statistics")
    public ResponseEntity<Map<EtatVoyage, Long>> getVoyageStatistics() {
        Map<EtatVoyage, Long> statistics = voyageService.getVoyagesStatistics();
        return ResponseEntity.ok(statistics);
    }

    @PutMapping("/changeEtat")
    public ResponseEntity<VoyageEtatDTO> changeEtat(@RequestBody VoyageEtatDTO voyageEtatDTO) {
        try {
            VoyageEtatDTO updatedVoyage = voyageService.changeVoyageEtat(voyageEtatDTO);
            return ResponseEntity.ok(updatedVoyage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/warnings/{id}")
    public ResponseEntity<List<String>> getVoyageWarnings(@PathVariable Long id) {
        VoyageDTO voyageDTO = voyageService.checkVoyageWarnings(id);
        Voyage voyage = voyageMapper.toEntity(voyageDTO);
        return ResponseEntity.ok(voyage.getWarnings());
    }


}