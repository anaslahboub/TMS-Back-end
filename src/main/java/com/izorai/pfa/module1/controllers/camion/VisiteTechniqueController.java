package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.visiteTechnique.VisiteTechniqueDto;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;
import com.izorai.pfa.module1.mappers.camion.VisiteTechniqueMapper;
import com.izorai.pfa.module1.services.camion.visiteTechnique.VisiteTechniqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visites-techniques")
@RequiredArgsConstructor
public class VisiteTechniqueController {

    private final VisiteTechniqueService visiteTechniqueService;
    private final VisiteTechniqueMapper visiteTechniqueMapper;



    @PostMapping
    public ResponseEntity<VisiteTechniqueDto> createVisiteTechnique(@RequestBody VisiteTechnique visiteTechnique) {
        VisiteTechniqueDto savedVisite = visiteTechniqueService.save(visiteTechnique);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVisite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisiteTechniqueDto> getVisiteTechniqueById(@PathVariable Long id) {
        VisiteTechniqueDto visite = visiteTechniqueService.getById(id);
        return ResponseEntity.ok(visite);
    }

    @GetMapping
    public ResponseEntity<List<VisiteTechniqueDto>> getAllVisitesTechniques() {
        List<VisiteTechniqueDto> visites = visiteTechniqueService.getAll();
        return ResponseEntity.ok(visites);
    }

    @GetMapping("/camion/{immatriculation}")
    public ResponseEntity<List<VisiteTechniqueDto>> getVisitesByCamion(@PathVariable String immatriculation) {
        List<VisiteTechniqueDto> visites = visiteTechniqueService.getByCamion(immatriculation);
        return ResponseEntity.ok(visites);
    }

    @GetMapping("/expirant")
    public ResponseEntity<List<VisiteTechniqueDto>> getVisitesExpirantDans30Jours() {
        List<VisiteTechniqueDto> visites = visiteTechniqueService.getVisitesTechniquesExpirantDans30Jours().stream().map(visiteTechniqueMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(visites);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisiteTechniqueDto> updateVisiteTechnique(
            @PathVariable Long id,
                 @RequestBody VisiteTechnique visiteTechnique) {
        VisiteTechniqueDto updatedVisite = visiteTechniqueService.update(id, visiteTechnique);
        return ResponseEntity.ok(updatedVisite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisiteTechnique(@PathVariable Long id) {
        visiteTechniqueService.delete(id);
        return ResponseEntity.noContent().build();
    }
}