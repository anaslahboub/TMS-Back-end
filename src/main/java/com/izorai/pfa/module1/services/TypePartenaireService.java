package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.TypePartenaire;

import java.util.List;
import java.util.Optional;

public interface TypePartenaireService {
    TypePartenaire addNewTypePartenaire(TypePartenaire typePartenaire);
    List<TypePartenaire> getAllTypePartenaires();
    Optional<TypePartenaire> getTypePartenaireById(Long idTypePartenaire);
    TypePartenaire updateTypePartenaire(Long idTypePartenaire, TypePartenaire typePartenaireDetails);
    void deleteTypePartenaire(Long idTypePartenaire);
}
