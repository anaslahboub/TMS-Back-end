package com.izorai.pfa.module2.services.marchandises;

import com.izorai.pfa.module2.DTO.marchandises.CategorieDTO;
import java.util.List;
import java.util.Optional;

public interface CategorieService {
    CategorieDTO createCategorie(CategorieDTO categorieDTO);
    List<CategorieDTO> getAllCategories();
    Optional<CategorieDTO> getCategorieById(Long id);
    Optional<CategorieDTO> getCategorieByNom(String nom);
    CategorieDTO updateCategorie(Long id, CategorieDTO categorieDTO);
    void deleteCategorie(Long id);
    boolean existsByNom(String nom);
}