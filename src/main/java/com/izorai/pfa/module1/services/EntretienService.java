package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Entretien;

import java.util.List;
import java.util.Optional;

public interface EntretienService {
    public Entretien createEntretien(Entretien entretien);
    public List<Entretien> getAllEntretiens();
    public Optional<Entretien> getEntretienById(Long id);
    public Entretien updateEntretien(Long id, Entretien entretienDetails);
    public void deleteEntretien(Long id);
}
