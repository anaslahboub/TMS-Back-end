package com.izorai.pfa.module1.services;

import com.izorai.pfa.module1.entities.CarteGrise;

import java.util.List;
import java.util.Optional;

public interface CarteGriseService {
    CarteGrise addNewCarteGrise(CarteGrise carteGrise);
    List<CarteGrise> getAllCarteGrises();
    Optional<CarteGrise> getCarteGriseById(Long id);
    CarteGrise updateCarteGrise(Long id, CarteGrise carteGriseDetails);
    void deleteCarteGrise(Long id);
}
