package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.marchandises.Marchandise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MarchandiseServiceImpl implements MarchandiseService {
    @Override
    public Marchandise addNewMarchandise(Marchandise marchandise) {
        return null;
    }

    @Override
    public List<Marchandise> getAllMarchandises() {
        return List.of();
    }

    @Override
    public Optional<Marchandise> getMarchandiseById(int id) {
        return Optional.empty();
    }

    @Override
    public Marchandise updateMarchandise(int id, Marchandise marchandiseDetails) {
        return null;
    }

    @Override
    public void deleteMarchandise(int id) {

    }
}
