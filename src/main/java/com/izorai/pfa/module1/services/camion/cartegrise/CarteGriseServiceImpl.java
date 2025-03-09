package com.izorai.pfa.module1.services.camion.cartegrise;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.entities.camion.CarteGrise;
import com.izorai.pfa.module1.mappers.camion.CarteGriseMapper;
import com.izorai.pfa.module1.repository.camion.CarteGriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CarteGriseServiceImpl implements CarteGriseService {

    private final CarteGriseRepository carteGriseRepository;
    private final CarteGriseMapper carteGriseMapper;

    public CarteGriseServiceImpl(CarteGriseRepository carteGriseRepository, CarteGriseMapper carteGriseMapper) {
        this.carteGriseRepository = carteGriseRepository;
        this.carteGriseMapper = carteGriseMapper;
    }


    @Override
    public CarteGriseDTO addNewCarteGrise(CarteGriseDTO carteGrise) {

        return carteGriseMapper.toCarteGriseDTO(carteGriseRepository.save(carteGriseMapper.fromCarteGriseDTO(carteGrise)));
    }

    @Override
    public List<CarteGriseDTO> getAllCarteGrises() {

        return carteGriseRepository.findAll().stream().map(carteGriseMapper::toCarteGriseDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CarteGriseDTO> getCarteGriseById(Long id) {
        return carteGriseRepository.findById(id).stream().map(carteGriseMapper::toCarteGriseDTO).findFirst();
    }

    @Override
    public CarteGriseDTO updateCarteGrise(Long id, CarteGriseDTO carteGriseDetails) {
        CarteGrise carte = carteGriseRepository.findById(id).map(carteGrise -> {
            carteGrise.setDateMiseEnCirculation(carteGriseDetails.dateMiseEnCirculation());
            carteGrise.setMarque(carteGriseDetails.marque());
            carteGrise.setGenre(carteGriseDetails.genre());
            carteGrise.setNumeroSerie(carteGriseDetails.numeroSerie());
            carteGrise.setCouleur(carteGriseDetails.couleur());
            carteGrise.setNombrePlace(carteGriseDetails.nombrePlace());
            carteGrise.setPuissanceFiscale(carteGriseDetails.puissanceFiscale());
            carteGrise.setEnergie(carteGriseDetails.energie());
            carteGrise.setProprietaire(carteGriseDetails.proprietaire());
            carteGrise.setPoidsVide(carteGriseDetails.poidsVide());
            carteGrise.setPoidsAutorise(carteGriseDetails.poidsAutorise());
            carteGrise.setDateDelivrance(carteGriseDetails.dateDelivrance());
            carteGrise.setAdress(carteGriseDetails.adress());
            return carteGriseRepository.save(carteGrise);
        }).orElseThrow(() -> new RuntimeException("Carte grise non trouvée"));

        return carteGriseMapper.toCarteGriseDTO(carte);
    }

    @Override
    public void deleteCarteGrise(Long id) {
        carteGriseRepository.deleteById(id); // Supprime la carte grise par son ID
    }

}
