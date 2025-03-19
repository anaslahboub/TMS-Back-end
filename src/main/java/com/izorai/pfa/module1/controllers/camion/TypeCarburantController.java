package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.typeCarburant.TypeCarburantDTO;
import com.izorai.pfa.module1.services.camion.typeCarburant.TypeCarburantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typeCarburants")
public class TypeCarburantController {

    private final TypeCarburantService typeCarburantService;

    public TypeCarburantController(TypeCarburantService typeCarburantService) {
        this.typeCarburantService = typeCarburantService;
    }

    // Add a new TypeCarburant
    @PostMapping
    public ResponseEntity<TypeCarburantDTO> addNewTypeCarburant(@RequestBody TypeCarburantDTO typeCarburantDTO) {
        TypeCarburantDTO newTypeCarburant = typeCarburantService.addNewTypeCarburant(typeCarburantDTO);
        return new ResponseEntity<>(newTypeCarburant, HttpStatus.CREATED);
    }

    // Get all TypeCarburants
    @GetMapping
    public ResponseEntity<List<TypeCarburantDTO>> getAllTypeCarburants() {
        List<TypeCarburantDTO> typeCarburants = typeCarburantService.getAllTypeCarburants();
        return new ResponseEntity<>(typeCarburants, HttpStatus.OK);
    }

    // Get a TypeCarburant by ID
    @GetMapping("/{id}")
    public ResponseEntity<TypeCarburantDTO> getTypeCarburantById(@PathVariable Long id) {
        Optional<TypeCarburantDTO> typeCarburantDTO = typeCarburantService.getTypeCarburantById(id);
        return typeCarburantDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a TypeCarburant
    @PutMapping("/{id}")
    public ResponseEntity<TypeCarburantDTO> updateTypeCarburant(@PathVariable Long id,
                                                                @RequestBody TypeCarburantDTO typeCarburantDTO) {
        TypeCarburantDTO updatedTypeCarburant = typeCarburantService.updateTypeCarburant(id, typeCarburantDTO);
        return new ResponseEntity<>(updatedTypeCarburant, HttpStatus.OK);
    }

    // Delete a TypeCarburant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeCarburant(@PathVariable Long id) {
        typeCarburantService.deleteTypeCarburant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}