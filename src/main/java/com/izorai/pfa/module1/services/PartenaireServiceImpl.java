package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Partenaire;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PartenaireServiceImpl implements PartenaireService {
    @Override
    public Partenaire createPartenaire(Partenaire partenaire) {
        return null;
    }

    @Override
    public List<Partenaire> getAllPartenaires() {
        return List.of();
    }

    @Override
    public Optional<Partenaire> getPartenaireById(Long idPartenaire) {
        return Optional.empty();
    }

    @Override
    public Partenaire updatePartenaire(Long idPartenaire, Partenaire partenaireDetails) {
        return null;
    }

    @Override
    public void deletePartenaire(Long idPartenaire) {

    }
}
