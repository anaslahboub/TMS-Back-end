package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;
import com.izorai.pfa.module1.services.camion.typeCamion.TypeCamionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typeCamions")
public class TypeCamionController {

    private final TypeCamionService typeCamionService;

    public TypeCamionController(TypeCamionService typeCamionService) {
        this.typeCamionService = typeCamionService;
    }

    // Add a new TypeCamion
    @PostMapping
    public ResponseEntity<TypeCamionDTO> addNewTypeCamion(@RequestBody TypeCamionDTO typeCamionDTO) {
        TypeCamionDTO newTypeCamion = typeCamionService.addNewTypeCamion(typeCamionDTO);
        return new ResponseEntity<>(newTypeCamion, HttpStatus.CREATED);
    }

    // Get all TypeCamions
    @GetMapping
    public ResponseEntity<List<TypeCamionDTO>> getAllTypeCamions() {
        List<TypeCamionDTO> typeCamions = typeCamionService.getAllTypeCamions();
        return new ResponseEntity<>(typeCamions, HttpStatus.OK);
    }

    // Get a TypeCamion by ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeCamionDTO> getTypeCamionById(@PathVariable Long id) {
        Optional<TypeCamionDTO> typeCamionDTO = typeCamionService.getTypeCamionById(id);
        return typeCamionDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a TypeCamion
    @PutMapping("/{id}")
    public ResponseEntity<TypeCamionDTO> updateTypeCamion(@PathVariable Long id,
                                                          @RequestBody TypeCamionDTO typeCamionDTO) {
        TypeCamionDTO updatedTypeCamion = typeCamionService.updateTypeCamion(id, typeCamionDTO);
        return new ResponseEntity<>(updatedTypeCamion, HttpStatus.OK);
    }

    // Delete a TypeCamion by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeCamion(@PathVariable Long id) {
        typeCamionService.deleteTypeCamion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}