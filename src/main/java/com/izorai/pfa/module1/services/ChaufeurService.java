package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Chaufeur;
import java.util.List;
import java.util.Optional;

public interface ChaufeurService {
    Chaufeur addNewChaufeur(Chaufeur chaufeur);
    List<Chaufeur> getAllChaufeurs();
    Optional<Chaufeur> getChaufeurById(Long id);
    Chaufeur updateChaufeur(Long id, Chaufeur chaufeurDetails);
    void deleteChaufeur(Long id);
}
