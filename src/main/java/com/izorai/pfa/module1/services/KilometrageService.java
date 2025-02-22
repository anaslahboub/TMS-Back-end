package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Kilometrage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface KilometrageService {
    public Kilometrage addNewKilometrage(Kilometrage kilometrage);
    public List<Kilometrage> getAllKilometrages();
    public Optional<Kilometrage> getKilometrageById(Long id);
    public Kilometrage updateKilometrage(Long id, Kilometrage kilometrageDetails);
    public void deleteKilometrage(Long id);
}
