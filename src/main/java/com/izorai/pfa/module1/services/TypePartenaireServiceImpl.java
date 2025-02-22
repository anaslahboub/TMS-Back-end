package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.TypePartenaire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TypePartenaireServiceImpl implements TypePartenaireService {
    @Override
    public TypePartenaire addNewTypePartenaire(TypePartenaire typePartenaire) {
        return null;
    }

    @Override
    public List<TypePartenaire> getAllTypePartenaires() {
        return List.of();
    }

    @Override
    public Optional<TypePartenaire> getTypePartenaireById(Long idTypePartenaire) {
        return Optional.empty();
    }

    @Override
    public TypePartenaire updateTypePartenaire(Long idTypePartenaire, TypePartenaire typePartenaireDetails) {
        return null;
    }

    @Override
    public void deleteTypePartenaire(Long idTypePartenaire) {

    }
}
