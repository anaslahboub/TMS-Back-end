package com.izorai.pfa.module1.services.camion.cartegrise;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;

import java.util.List;
import java.util.Optional;

public interface CarteGriseService {
    /// CARTE GRISE SERVICE CRUD

    public CarteGriseDTO addNewCarteGrise(CarteGriseDTO carteGrise);
    public List<CarteGriseDTO> getAllCarteGrises();
    public Optional<CarteGriseDTO> getCarteGriseById(Long id);
    public CarteGriseDTO updateCarteGrise(Long id, CarteGriseDTO carteGriseDetails);
    public void deleteCarteGrise(Long id);
}
