package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Chaufeur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChaufeurServiceImpl implements ChaufeurService {
    @Override
    public Chaufeur addNewChaufeur(Chaufeur chaufeur) {
        return null;
    }

    @Override
    public List<Chaufeur> getAllChaufeurs() {
        return List.of();
    }

    @Override
    public Optional<Chaufeur> getChaufeurById(Long id) {
        return Optional.empty();
    }

    @Override
    public Chaufeur updateChaufeur(Long id, Chaufeur chaufeurDetails) {
        return null;
    }

    @Override
    public void deleteChaufeur(Long id) {

    }
}
