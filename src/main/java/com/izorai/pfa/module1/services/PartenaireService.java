package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Partenaire;

import java.util.List;
import java.util.Optional;

public interface PartenaireService {
    Partenaire createPartenaire(Partenaire partenaire);
    List<Partenaire> getAllPartenaires();
    Optional<Partenaire> getPartenaireById(Long idPartenaire);
    Partenaire updatePartenaire(Long idPartenaire, Partenaire partenaireDetails);
    void deletePartenaire(Long idPartenaire);
}
