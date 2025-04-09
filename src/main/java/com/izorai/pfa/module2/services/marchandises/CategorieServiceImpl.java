package com.izorai.pfa.module2.services.marchandises;

import com.izorai.pfa.module2.DTO.marchandises.CategorieDTO;
import com.izorai.pfa.module2.entities.marchandises.Categorie;
import com.izorai.pfa.module2.mappers.Marchandises.CategorieMapper;
import com.izorai.pfa.module2.repository.marchandises.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;



    @Override
    public CategorieDTO createCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = categorieMapper.toEntity(categorieDTO);
        System.out.println("categorie : " + categorie.getLibelle());
        Categorie saved = categorieRepository.save(categorie);
        System.out.println("saved : " + saved.getLibelle());
        return categorieMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll().stream()
                .map(categorieMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieDTO> getCategorieById(Long id) {
        return categorieRepository.findById(id)
                .map(categorieMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategorieDTO> getCategorieByNom(String nom) {
        return categorieRepository.findByLibelle(nom)
                .map(categorieMapper::toDto);
    }

    @Override
    public CategorieDTO updateCategorie(Long id, CategorieDTO categorieDTO) {
        return categorieRepository.findById(id)
                .map(existing -> {
                    Categorie updated = categorieMapper.toEntity(categorieDTO);
                    updated.setId(id);
                    Categorie saved = categorieRepository.save(updated);
                    return categorieMapper.toDto(saved);
                })
                .orElseThrow(() -> new RuntimeException("Categorie not found with id: " + id));
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public boolean existsByNom(String nom) {
        return categorieRepository.existsByLibelle(nom);
    }
}