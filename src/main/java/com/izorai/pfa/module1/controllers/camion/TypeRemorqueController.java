package com.izorai.pfa.module1.controllers.camion;

import com.izorai.pfa.module1.DTO.camion.typeRemorque.TypeRemorqueDTO;
import com.izorai.pfa.module1.services.camion.typeRemorque.TypeRemorqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typeRemorques")
public class TypeRemorqueController {

    private final TypeRemorqueService typeRemorqueService;

    // Constructor injection for TypeRemorqueService
    public TypeRemorqueController(TypeRemorqueService typeRemorqueService) {
        this.typeRemorqueService = typeRemorqueService;
    }

    /**
     * Add a new TypeRemorque.
     *
     * @param typeRemorqueDTO The DTO containing the data for the new TypeRemorque.
     * @return The created TypeRemorqueDTO and HTTP status 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<TypeRemorqueDTO> addNewTypeRemorque(@RequestBody TypeRemorqueDTO typeRemorqueDTO) {
        TypeRemorqueDTO newTypeRemorque = typeRemorqueService.addNewTypeRemorque(typeRemorqueDTO);
        return new ResponseEntity<>(newTypeRemorque, HttpStatus.CREATED);
    }

    /**
     * Get all TypeRemorques.
     *
     * @return A list of all TypeRemorqueDTOs and HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<TypeRemorqueDTO>> getAllTypeRemorques() {
        List<TypeRemorqueDTO> typeRemorques = typeRemorqueService.getAllTypeRemorques();
        return new ResponseEntity<>(typeRemorques, HttpStatus.OK);
    }

    /**
     * Get a TypeRemorque by its ID.
     *
     * @param id The ID of the TypeRemorque to retrieve.
     * @return The TypeRemorqueDTO if found, or HTTP status 404 (NOT FOUND) if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeRemorqueDTO> getTypeRemorqueById(@PathVariable Long id) {
        Optional<TypeRemorqueDTO> typeRemorqueDTO = typeRemorqueService.getTypeRemorqueById(id);
        return typeRemorqueDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Update an existing TypeRemorque.
     *
     * @param id              The ID of the TypeRemorque to update.
     * @param typeRemorqueDTO The DTO containing the updated data.
     * @return The updated TypeRemorqueDTO and HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<TypeRemorqueDTO> updateTypeRemorque(@PathVariable Long id,
                                                              @RequestBody TypeRemorqueDTO typeRemorqueDTO) {
        TypeRemorqueDTO updatedTypeRemorque = typeRemorqueService.updateTypeRemorque(id, typeRemorqueDTO);
        return new ResponseEntity<>(updatedTypeRemorque, HttpStatus.OK);
    }

    /**
     * Delete a TypeRemorque by its ID.
     *
     * @param id The ID of the TypeRemorque to delete.
     * @return HTTP status 204 (NO CONTENT) if successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeRemorque(@PathVariable Long id) {
        typeRemorqueService.deleteTypeRemorque(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}