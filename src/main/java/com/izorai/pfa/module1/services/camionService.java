package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Camion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface camionService {
    public Camion addNewCamion(Camion camion);
    public List<Camion> getAllCamions();
    public Optional<Camion> getCamionById(Long immatriculation);
    public Camion updateCamion(Long immatriculation, Camion camionDetails);
    public void deleteCamion(Long immatriculation);
}
