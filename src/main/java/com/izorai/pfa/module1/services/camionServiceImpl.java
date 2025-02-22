package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Camion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class camionServiceImpl implements camionService {
    @Override
    public Camion addNewCamion(Camion camion) {
        return null;
    }

    @Override
    public List<Camion> getAllCamions() {
        return List.of();
    }

    @Override
    public Optional<Camion> getCamionById(Long immatriculation) {
        return Optional.empty();
    }

    @Override
    public Camion updateCamion(Long immatriculation, Camion camionDetails) {
        return null;
    }

    @Override
    public void deleteCamion(Long immatriculation) {

    }
}
