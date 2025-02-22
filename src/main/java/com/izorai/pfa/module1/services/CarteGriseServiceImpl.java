package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.CarteGrise;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarteGriseServiceImpl implements CarteGriseService {
    @Override
    public CarteGrise addNewCarteGrise(CarteGrise carteGrise) {
        return null;
    }

    @Override
    public List<CarteGrise> getAllCarteGrises() {
        return List.of();
    }

    @Override
    public Optional<CarteGrise> getCarteGriseById(Long id) {
        return Optional.empty();
    }

    @Override
    public CarteGrise updateCarteGrise(Long id, CarteGrise carteGriseDetails) {
        return null;
    }

    @Override
    public void deleteCarteGrise(Long id) {

    }
}
