package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.partenaire.paretenaire.PartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.services.partenaire.partenaire.PartenaireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {

    private final PartenaireService partenaireService;


    public PartenaireController(PartenaireService partenaireService) {
        this.partenaireService = partenaireService;
    }


    @PostMapping
    public ResponseEntity<PartenaireCreateDTO> createPartenaire(@RequestBody PartenaireCreateDTO dto) {
        return ResponseEntity.ok(partenaireService.createPartenaire(dto));
    }

    @GetMapping
    public ResponseEntity<List<PartenaireRespDTO>> listPartenaires() {
        return ResponseEntity.ok(partenaireService.getAllPartenaires());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartenaireRespDTO> getPartenaireById(@PathVariable Long id) {
        return ResponseEntity.ok(partenaireService.getPartenaireById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        partenaireService.deletePartenaire(id);
        return ResponseEntity.noContent().build(); // Retourne 204 No Content
    }




    // Gestion des Personnes Physiques





}
