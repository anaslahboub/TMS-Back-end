package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.Kilometrage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class KilometrageServiceImpl implements KilometrageService {
    @Override
    public Kilometrage addNewKilometrage(Kilometrage kilometrage) {
        return null;
    }

    @Override
    public List<Kilometrage> getAllKilometrages() {
        return List.of();
    }

    @Override
    public Optional<Kilometrage> getKilometrageById(Long id) {
        return Optional.empty();
    }

    @Override
    public Kilometrage updateKilometrage(Long id, Kilometrage kilometrageDetails) {
        return null;
    }

    @Override
    public void deleteKilometrage(Long id) {

    }
}
