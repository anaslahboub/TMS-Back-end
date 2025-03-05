package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.services.PartenaireService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity<PartenaireRespDTO> getPartenaireById(@PathVariable Long id) {
        return ResponseEntity.ok(partenaireService.getPartenaireById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        partenaireService.deletePartenaire(id);
        return ResponseEntity.noContent().build(); // Retourne 204 No Content
    }
    @PostMapping("/morale")
    public ResponseEntity<MoraleRespDTO> addMorale(@RequestBody MoraleCreateDTO moraleCreateDTO) {
        MoraleRespDTO moraleRespDTO = partenaireService.addNewMorale(moraleCreateDTO);
        return new ResponseEntity<>(moraleRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer toutes les Personnes Morales
    @GetMapping("/morales")
    public ResponseEntity<List<MoraleRespDTO>> getAllMorales() {
        List<MoraleRespDTO> morales = partenaireService.getAllMorales();
        return ResponseEntity.ok(morales);
    }

    // Endpoint pour récupérer une Personne Morale par son ID
    @GetMapping("/morale/{id}")
    public ResponseEntity<MoraleRespDTO> getMoraleById(@PathVariable Long id) {
        MoraleRespDTO moraleRespDTO = partenaireService.getMoraleById(id);
        return ResponseEntity.ok(moraleRespDTO);
    }

    // Endpoint pour mettre à jour une Personne Morale existante
    @PutMapping("/morale/{id}")
    public ResponseEntity<MoraleRespDTO> updateMorale(@PathVariable Long id, @RequestBody MoraleCreateDTO moraleCreateDTO) {
        MoraleRespDTO moraleRespDTO = partenaireService.updateMorale(id, moraleCreateDTO);
        return ResponseEntity.ok(moraleRespDTO);
    }

    // Endpoint pour supprimer une Personne Morale
    @DeleteMapping("/morale/{id}")
    public ResponseEntity<Void> deleteMorale(@PathVariable Long id) {
        partenaireService.deleteMorale(id);
        return ResponseEntity.noContent().build();
    }

    // Gestion des Personnes Physiques

    // Endpoint pour ajouter une nouvelle Personne Physique
    @PostMapping("/physique")
    public ResponseEntity<PhysiqueRespDTO> addPhysique(@RequestBody PhysiqueCreateDTO physiqueCreateDTO) {
        PhysiqueRespDTO physiqueRespDTO = partenaireService.addNewPhysique(physiqueCreateDTO);
        return new ResponseEntity<>(physiqueRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer toutes les Personnes Physiques
    @GetMapping("/physiques")
    public ResponseEntity<List<PhysiqueRespDTO>> getAllPhysiques() {
        List<PhysiqueRespDTO> physiques = partenaireService.getAllPhysiques();
        return ResponseEntity.ok(physiques);
    }

    // Endpoint pour récupérer une Personne Physique par son ID
    @GetMapping("/physique/{id}")
    public ResponseEntity<PhysiqueRespDTO> getPhysiqueById(@PathVariable Long id) {
        PhysiqueRespDTO physiqueRespDTO = partenaireService.getPhysiqueById(id);
        return ResponseEntity.ok(physiqueRespDTO);
    }

    // Endpoint pour mettre à jour une Personne Physique existante
    @PutMapping("/physique/{id}")
    public ResponseEntity<PhysiqueRespDTO> updatePhysique(@PathVariable Long id, @RequestBody PhysiqueCreateDTO physiqueCreateDTO) {
        PhysiqueRespDTO physiqueRespDTO = partenaireService.updatePhysique(id, physiqueCreateDTO);
        return ResponseEntity.ok(physiqueRespDTO);
    }

    // Endpoint pour supprimer une Personne Physique
    @DeleteMapping("/physique/{id}")
    public ResponseEntity<Void> deletePhysique(@PathVariable Long id) {
        partenaireService.deletePhysique(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/typePartenaires")
    public ResponseEntity<TypePartenaireRespDTO> addTypePartenaire(@RequestBody TypePartenaireCreateDTO typePartenaireCreateDTO) {
        TypePartenaireRespDTO typePartenaireRespDTO = partenaireService.addNewTypePartenaire(typePartenaireCreateDTO);
        return new ResponseEntity<>(typePartenaireRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les TypePartenaires
    @GetMapping("/typePartenaires")
    public ResponseEntity<List<TypePartenaireRespDTO>> getAllTypePartenaires() {
        List<TypePartenaireRespDTO> typePartenaires = partenaireService.getAllTypePartenaires();
        return ResponseEntity.ok(typePartenaires);
    }

    // Endpoint pour récupérer un TypePartenaire par son ID
    @GetMapping("/typePartenaires/{id}")
    public ResponseEntity<TypePartenaireRespDTO> getTypePartenaireById(@PathVariable Long id) {
        TypePartenaireRespDTO typePartenaireRespDTO = partenaireService.getTypePartenaireById(id);
        return ResponseEntity.ok(typePartenaireRespDTO);
    }

    // Endpoint pour mettre à jour un TypePartenaire
    @PutMapping("/typePartenaires/{id}")
    public ResponseEntity<TypePartenaireRespDTO> updateTypePartenaire(@PathVariable Long id,
                                                                      @RequestBody TypePartenaireCreateDTO typePartenaireCreateDTO) {
        TypePartenaireRespDTO typePartenaireRespDTO = partenaireService.updateTypePartenaire(id, typePartenaireCreateDTO);
        return ResponseEntity.ok(typePartenaireRespDTO);
    }

    // Endpoint pour supprimer un TypePartenaire
    @DeleteMapping("/typePartenaires/{id}")
    public ResponseEntity<Void> deleteTypePartenaire(@PathVariable Long id) {
        partenaireService.deleteTypePartenaire(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/adresses")
    public ResponseEntity<Adress> addNewAdress(@RequestBody Adress adress) {
        Adress newAdress = partenaireService.addNewAdress(adress);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAdress);
    }

    // Récupérer toutes les adresses
    @GetMapping("/adresses")
    public ResponseEntity<List<Adress>> getAllAdresses() {
        List<Adress> adresses = partenaireService.getAllAdresses();
        return ResponseEntity.ok(adresses);
    }

    // Récupérer une adresse par ID
    @GetMapping("/adresses/{id}")
    public ResponseEntity<Adress> getAdressById(@PathVariable Long id) {
        Adress adress = partenaireService.getAdressById(id);
        return adress != null ? ResponseEntity.ok(adress) : ResponseEntity.notFound().build();
    }

    // Mettre à jour une adresse
    @PutMapping("/adresses/{id}")
    public ResponseEntity<Adress> updateAdress(@PathVariable Long id, @RequestBody Adress adressDetails) {
        Adress updatedAdress = partenaireService.updateAdress( adressDetails);
        return updatedAdress != null ? ResponseEntity.ok(updatedAdress) : ResponseEntity.notFound().build();
    }

    // Supprimer une adresse
    @DeleteMapping("/adresses/{id}")
    public ResponseEntity<Void> deleteAdress(@PathVariable Long id) {
        partenaireService.deleteAdress(id);
        return ResponseEntity.noContent().build();
    }

}
