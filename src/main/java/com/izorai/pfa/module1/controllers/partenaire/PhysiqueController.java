package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateAdressDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.services.partenaire.physique.PhysiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/physiques")
public class PhysiqueController {
    private final PhysiqueService physiqueService;

    public PhysiqueController(PhysiqueService physiqueService) {
        this.physiqueService = physiqueService;
    }



       @PostMapping
    public ResponseEntity<PhysiqueRespDTO> addPhysique(@RequestBody PhysiqueCreateAdressDTO physiqueCreateDTO) {
        PhysiqueRespDTO physiqueRespDTO = physiqueService.addNewPhysiqueAdress(physiqueCreateDTO);
        return new ResponseEntity<>(physiqueRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer toutes les Personnes Physiques
    @GetMapping
    public ResponseEntity<List<PhysiqueRespDTO>> getAllPhysiques() {
        List<PhysiqueRespDTO> physiques = physiqueService.getAllPhysiques();
        return ResponseEntity.ok(physiques);
    }



    // Endpoint pour récupérer une Personne Physique par son ID
    @GetMapping("/{id}")
    public ResponseEntity<PhysiqueRespDTO> getPhysiqueById(@PathVariable Long id) {
        PhysiqueRespDTO physiqueRespDTO = physiqueService.getPhysiqueById(id);
        return ResponseEntity.ok(physiqueRespDTO);
    }

    // Endpoint pour mettre à jour une Personne Physique existante
    @PutMapping("/{id}")
    public ResponseEntity<PhysiqueRespDTO> updatePhysique(@PathVariable Long id, @RequestBody PhysiqueCreateDTO physiqueCreateDTO) {
        PhysiqueRespDTO physiqueRespDTO = physiqueService.updatePhysique(id, physiqueCreateDTO);
        return ResponseEntity.ok(physiqueRespDTO);
    }

    // Endpoint pour supprimer une Personne Physique
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhysique(@PathVariable Long id) {
        physiqueService.deletePhysique(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/adresses")
    public ResponseEntity<List<Adress>> getPhysiqueAddresses(@PathVariable Long id) {
        List<Adress> physiqueRespDTO = physiqueService.getAdressesPhysique(id);
        return ResponseEntity.ok(physiqueRespDTO);

    }
    @PostMapping("/{id}/addAddresses")
    public ResponseEntity<Adress> addPhysiqueAdress(@PathVariable Long id, @RequestBody AdressCreateDto adressCreateDto){
        return ResponseEntity.ok(physiqueService.addAdressPhysique(id, adressCreateDto));
    }
}
