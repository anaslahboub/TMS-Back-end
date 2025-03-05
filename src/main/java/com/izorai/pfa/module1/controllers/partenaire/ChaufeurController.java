package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.services.PartenaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChaufeurController {

    private final PartenaireService chaufeurService;

    @Autowired
    public ChaufeurController(PartenaireService chaufeurService) {
        this.chaufeurService = chaufeurService;
    }

    // Endpoint pour ajouter un chauffeur
    @PostMapping
    public ResponseEntity<ChaufeurRespDTO> addChaufeur(@RequestBody ChaufeurCreateDTO chaufeurCreateDTO) {
        ChaufeurRespDTO chaufeurRespDTO = chaufeurService.addNewChaufeur(chaufeurCreateDTO);
        return new ResponseEntity<>(chaufeurRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les chauffeurs
    @GetMapping
    public ResponseEntity<List<ChaufeurRespDTO>> getAllChaufeurs() {
        List<ChaufeurRespDTO> chauffeurs = chaufeurService.getAllChaufeurs();
        return ResponseEntity.ok(chauffeurs);
    }

    // Endpoint pour récupérer un chauffeur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ChaufeurRespDTO> getChaufeurById(@PathVariable Long idChauffeur) {
        ChaufeurRespDTO chaufeurRespDTO = chaufeurService.getChaufeurById(idChauffeur);
        return ResponseEntity.ok(chaufeurRespDTO);
    }

    // Endpoint pour mettre à jour un chauffeur
    @PutMapping("/{id}")
    public ResponseEntity<ChaufeurRespDTO> updateChaufeur(@PathVariable Long id,
                                                          @RequestBody ChaufeurCreateDTO chaufeurCreateDTO) {
        ChaufeurRespDTO chaufeurRespDTO = chaufeurService.updateChaufeur(id, chaufeurCreateDTO);
        return ResponseEntity.ok(chaufeurRespDTO);
    }

    // Endpoint pour supprimer un chauffeur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChaufeur(@PathVariable Long idChauffeur) {
        chaufeurService.deleteChaufeur(idChauffeur);
        return ResponseEntity.noContent().build();
    }
}
