package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.services.partenaire.morale.MoraleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/morales")
public class MoraleController {

    private final MoraleService moraleService;

    public MoraleController(MoraleService moraleService) {
        this.moraleService = moraleService;
    }


    @PostMapping
    public ResponseEntity<MoraleRespDTO> addMorale(@RequestBody MoraleCreateDTO moraleCreateDTO) {
        MoraleRespDTO moraleRespDTO = moraleService.addNewMorale(moraleCreateDTO);
        return new ResponseEntity<>(moraleRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer toutes les Personnes Morales
    @GetMapping
    public ResponseEntity<List<MoraleRespDTO>> getAllMorales() {
        List<MoraleRespDTO> morales = moraleService.getAllMorales();
        return ResponseEntity.ok(morales);
    }

    // Endpoint pour récupérer une Personne Morale par son ID
    @GetMapping("/{id}")
    public ResponseEntity<MoraleRespDTO> getMoraleById(@PathVariable Long id) {
        MoraleRespDTO moraleRespDTO = moraleService.getMoraleById(id);
        return ResponseEntity.ok(moraleRespDTO);
    }

    // Endpoint pour mettre à jour une Personne Morale existante
    @PutMapping("/{id}")
    public ResponseEntity<MoraleRespDTO> updateMorale(@PathVariable Long id, @RequestBody MoraleCreateDTO moraleCreateDTO) {
        MoraleRespDTO moraleRespDTO = moraleService.updateMorale(id, moraleCreateDTO);
        return ResponseEntity.ok(moraleRespDTO);
    }

    // Endpoint pour supprimer une Personne Morale
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMorale(@PathVariable Long id) {
        moraleService.deleteMorale(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<Adress>> getMoraleAddresses(@PathVariable Long id) {
        List<Adress> moraleRespDTO = moraleService.getAdressesMorale(id);
        return ResponseEntity.ok(moraleRespDTO);
    }

}
