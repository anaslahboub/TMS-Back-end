package com.izorai.pfa.module2.controllers;

import com.izorai.pfa.module2.DTO.marchandises.CategorieDTO;
import com.izorai.pfa.module2.services.marchandises.CategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    public ResponseEntity<CategorieDTO> createCategorie(@RequestBody CategorieDTO categorieDTO) {
        CategorieDTO newCategorie = categorieService.createCategorie(categorieDTO);
        return new ResponseEntity<>(newCategorie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        List<CategorieDTO> categories = categorieService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieDTO> getCategorieById(@PathVariable Long id) {
        Optional<CategorieDTO> categorieDTO = categorieService.getCategorieById(id);
        return categorieDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<CategorieDTO> getCategorieByNom(@PathVariable String nom) {
        Optional<CategorieDTO> categorieDTO = categorieService.getCategorieByNom(nom);
        return categorieDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieDTO> updateCategorie(
            @PathVariable Long id,
            @RequestBody CategorieDTO categorieDTO) {
        CategorieDTO updatedCategorie = categorieService.updateCategorie(id, categorieDTO);
        return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable Long id) {
        categorieService.deleteCategorie(id);
        return ResponseEntity.noContent().build();
    }
}