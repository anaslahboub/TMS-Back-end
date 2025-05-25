package com.izorai.pfa.module2.controllers;


import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module2.DTO.demande.DemandeCotationDto;
import com.izorai.pfa.module2.enumerations.StatusDemandeCotation;
import com.izorai.pfa.module2.services.demande.DemandeCotationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demandes-cotation")
@AllArgsConstructor
public class DemandeCotationController {

    private final DemandeCotationService demandeCotationService;



    @PostMapping
    public ResponseEntity<DemandeCotationDto> createDemande(@RequestBody DemandeCotationDto demandeDto) {
        DemandeCotationDto createdDemande = demandeCotationService.createDemande(demandeDto);
        return new ResponseEntity<>(createdDemande, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeCotationDto> updateDemande(
            @PathVariable Long id,
             @RequestBody DemandeCotationDto demandeDto) {
        DemandeCotationDto updatedDemande = demandeCotationService.updateDemande(id, demandeDto);
        return ResponseEntity.ok(updatedDemande);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDemande(@PathVariable Long id) {
        demandeCotationService.deleteDemande(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeCotationDto> getDemandeById(@PathVariable Long id) {
        return demandeCotationService.getDemandeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DemandeCotationDto>> getAllDemandes() {
        List<DemandeCotationDto> demandes = demandeCotationService.getAllDemandes();
        return ResponseEntity.ok(demandes);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DemandeCotationDto> updateStatus(
            @PathVariable Long id,
            @RequestParam StatusDemandeCotation newStatus) {
        DemandeCotationDto updatedDemande = demandeCotationService.updateStatus(id, newStatus);
        return ResponseEntity.ok(updatedDemande);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<DemandeCotationDto>> getDemandesByStatus(
            @RequestParam StatusDemandeCotation status) {
        List<DemandeCotationDto> demandes = demandeCotationService.getDemandesByStatus(status);
        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DemandeCotationDto>> searchByTypeMarchandise(
            @RequestParam String type) {
        List<DemandeCotationDto> demandes = demandeCotationService.searchByTypeMarchandise(type);
        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<StatusDemandeCotation, Long>> getDemandesStatistics() {
        Map<StatusDemandeCotation, Long> stats = demandeCotationService.getDemandesStatistics();
        return ResponseEntity.ok(stats);
    }

    @PutMapping("/{id}/assign-physique")
    public ResponseEntity<DemandeCotationDto> assignPhysique(
            @PathVariable Long id,
            @RequestParam Long physiqueId) {
        DemandeCotationDto updatedDemande = demandeCotationService.assignPhysique(id, physiqueId);
        return ResponseEntity.ok(updatedDemande);
    }

    @PutMapping("/{id}/chargement-address")
    public ResponseEntity<DemandeCotationDto> updateChargementAddress(
            @PathVariable Long id,
            @RequestBody Adress newAddress) {
        DemandeCotationDto updatedDemande = demandeCotationService.updateChargementAddress(id, newAddress);
        return ResponseEntity.ok(updatedDemande);
    }

    @PutMapping("/{id}/dechargement-address")
    public ResponseEntity<DemandeCotationDto> updateDechargementAddress(
            @PathVariable Long id,
            @RequestBody Adress newAddress) {
        DemandeCotationDto updatedDemande = demandeCotationService.updateDechargementAddress(id, newAddress);
        return ResponseEntity.ok(updatedDemande);
    }
}