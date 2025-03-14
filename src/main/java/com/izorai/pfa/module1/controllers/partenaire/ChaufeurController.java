package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.services.partenaire.chaufeur.ChaufeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChaufeurController {

    private final ChaufeurService chaufeurService;

    @Autowired
    public ChaufeurController(ChaufeurService chaufeurService) {
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
    @GetMapping("/{idPartenaire}")
    public ResponseEntity<ChaufeurRespDTO> getChaufeur(@PathVariable Long idPartenaire) {
        ChaufeurRespDTO chaufeurRespDTO = chaufeurService.getChaufeurById(idPartenaire);
        return ResponseEntity.ok(chaufeurRespDTO);
    }

    // Endpoint pour mettre à jour un chauffeur
    @PutMapping("/{idPartenaire}")
    public ResponseEntity<ChaufeurRespDTO> updateChaufeur(@PathVariable Long idPartenaire,
                                                          @RequestBody ChaufeurCreateDTO chaufeurCreateDTO) {
        ChaufeurRespDTO chaufeurRespDTO = chaufeurService.updateChaufeur(idPartenaire, chaufeurCreateDTO);
        return ResponseEntity.ok(chaufeurRespDTO);
    }

    // Endpoint pour supprimer un chauffeur
    @DeleteMapping("/{idPartenaire}")
    public ResponseEntity<Void> deleteChaufeur(@PathVariable Long idPartenaire) {
        chaufeurService.deleteChaufeur(idPartenaire);
        return ResponseEntity.noContent().build();
    }
}
