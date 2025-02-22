package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Entretien;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntretienServiceImpl implements EntretienService {
    @Override
    public Entretien createEntretien(Entretien entretien) {
        return null;
    }

    @Override
    public List<Entretien> getAllEntretiens() {
        return List.of();
    }

    @Override
    public Optional<Entretien> getEntretienById(Long id) {
        return Optional.empty();
    }

    @Override
    public Entretien updateEntretien(Long id, Entretien entretienDetails) {
        return null;
    }

    @Override
    public void deleteEntretien(Long id) {

    }
}
