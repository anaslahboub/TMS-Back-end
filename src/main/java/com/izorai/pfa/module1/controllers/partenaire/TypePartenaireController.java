package com.izorai.pfa.module1.controllers.partenaire;

import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireNomDto;
import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import com.izorai.pfa.module1.services.partenaire.typepartenaire.TypePartenaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/typePartenaires")
public class TypePartenaireController {

    private final TypePartenaireService typePartenaireService;

    public TypePartenaireController(TypePartenaireService typePartenaireService) {
        this.typePartenaireService = typePartenaireService;
    }


    @PostMapping
    public ResponseEntity<TypePartenaireRespDTO> addTypePartenaire(@RequestBody TypePartenaireCreateDTO typePartenaireCreateDTO) {
        TypePartenaireRespDTO typePartenaireRespDTO = typePartenaireService.addNewTypePartenaire(typePartenaireCreateDTO);
        return new ResponseEntity<>(typePartenaireRespDTO, HttpStatus.CREATED);
    }

    // Endpoint pour récupérer tous les TypePartenaires
    @GetMapping
    public ResponseEntity<List<TypePartenaireRespDTO>> getAllTypePartenaires() {
        List<TypePartenaireRespDTO> typePartenaires = typePartenaireService.getAllTypePartenaires();
        return ResponseEntity.ok(typePartenaires);
    }
    @GetMapping("/noms")
    public ResponseEntity<List<TypePartenaireNomDto>> getAllTypePartenaireNoms() {
        List<TypePartenaireNomDto> typePartenaires = typePartenaireService.getAllTypePartenaireNoms();
        return ResponseEntity.ok(typePartenaires);
    }
    @GetMapping("/nom/{nom}")
    public ResponseEntity<TypePartenaire> getTypePartenaireByyNom(@RequestBody String nom) {
        TypePartenaire typePartenaires = typePartenaireService.getPartenaireByLibelle(nom);
        return ResponseEntity.ok(typePartenaires);
    }


    // Endpoint pour récupérer un TypePartenaire par son ID
    @GetMapping("/{id}")
    public ResponseEntity<TypePartenaireRespDTO> getTypePartenaireById(@PathVariable Long id) {
        TypePartenaireRespDTO typePartenaireRespDTO = typePartenaireService.getTypePartenaireById(id);
        return ResponseEntity.ok(typePartenaireRespDTO);
    }

    // Endpoint pour mettre à jour un TypePartenaire
    @PutMapping("/{id}")
    public ResponseEntity<TypePartenaireRespDTO> updateTypePartenaire(@PathVariable Long id,
                                                                      @RequestBody TypePartenaireCreateDTO typePartenaireCreateDTO) {
        TypePartenaireRespDTO typePartenaireRespDTO = typePartenaireService.updateTypePartenaire(id, typePartenaireCreateDTO);
        return ResponseEntity.ok(typePartenaireRespDTO);
    }

    // Endpoint pour supprimer un TypePartenaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypePartenaire(@PathVariable Long id) {
        typePartenaireService.deleteTypePartenaire(id);
        return ResponseEntity.noContent().build();
    }
}
