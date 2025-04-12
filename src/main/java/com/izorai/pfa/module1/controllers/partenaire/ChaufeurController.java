package com.izorai.pfa.module1.controllers.partenaire;



import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurPermisDto;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurUpdateDto;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.services.partenaire.chaufeur.ChaufeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
                                                          @RequestBody ChaufeurUpdateDto chaufeurCreateDTO) {
        ChaufeurRespDTO chaufeurRespDTO = chaufeurService.updateChaufeur(idPartenaire, chaufeurCreateDTO);
        return ResponseEntity.ok(chaufeurRespDTO);
    }

    // Endpoint pour supprimer un chauffeur
    @DeleteMapping("/{idPartenaire}")
    public ResponseEntity<Void> deleteChaufeur(@PathVariable Long idPartenaire) {
        chaufeurService.deleteChaufeur(idPartenaire);
        return ResponseEntity.noContent().build();
    }


    // Driver Availability Operations
    @GetMapping("/available")
    public ResponseEntity<List<ChaufeurRespDTO>> getAvailableChauffeurs() {
        List<ChaufeurRespDTO> availableChauffeurs = chaufeurService.getChaufeursDisponibles();
        return ResponseEntity.ok(availableChauffeurs);
    }

    @PatchMapping("/{idPartenaire}/availability")
    public ResponseEntity<Void> changeAvailability(@PathVariable Long idPartenaire) {
        chaufeurService.changeDisponibilite(idPartenaire);
        return ResponseEntity.ok().build();
    }



    // Insurance Management
    @GetMapping("/{idPartenaire}/permis/valid")
    public ResponseEntity<Boolean> icheckPermisValidity(@PathVariable Long idPartenaire) {
        Boolean isValid = chaufeurService.isPermisValid(idPartenaire);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/permis/expired")
    public ResponseEntity<List<ChaufeurRespDTO>> getDriversWithExpiredPermis() {
        List<ChaufeurRespDTO> drivers = chaufeurService.findDriversWithExpiredPermis();
        return ResponseEntity.ok(drivers);
    }

    @PatchMapping("/{idPartenaire}/permis")
    public ResponseEntity<ChaufeurRespDTO> updatePermisExpiration(@PathVariable Long idPartenaire,
                                                                     @RequestParam LocalDate newExpirationDate) {
        Chaufeur updatedChauffeur = chaufeurService.updatePermisExpirationDate(idPartenaire, newExpirationDate);
        return ResponseEntity.ok(chaufeurService.getChaufeurById(updatedChauffeur.getIdPartenaire()));
    }



    // Statistical Operations
    @GetMapping("/stats/active")
    public ResponseEntity<Integer> getActiveDriversCount() {
        int count = chaufeurService.getNombreChaufeursActifs();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stats/on-mission")
    public ResponseEntity<Integer> getDriversOnMissionCount() {
        int count = chaufeurService.getNombreChaufeursEnMission();
        return ResponseEntity.ok(count);
    }


    @GetMapping("/{idPartenaire}/permis")
    public ResponseEntity<ChaufeurPermisDto> getPermisPhoto(@PathVariable Long idPartenaire) {
        ChaufeurPermisDto chaufeurRespDTO = chaufeurService.getPermisPhoto(idPartenaire);
        return ResponseEntity.ok(chaufeurRespDTO);
    }


}
