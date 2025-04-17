package com.izorai.pfa.module1.services.camion.cartegrise;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.CarteGrise;
import com.izorai.pfa.module1.mappers.camion.CarteGriseMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.repository.camion.CarteGriseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CarteGriseServiceImpl implements CarteGriseService {

    private final CarteGriseRepository carteGriseRepository;
    private final CarteGriseMapper carteGriseMapper;
    private final CamionRepository CamionRepository;
    private final CamionRepository camionRepository;


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
            carteGrise.setDateMiseEnCirculation(carteGriseDetails.getDateMiseEnCirculation());
            carteGrise.setMarque(carteGriseDetails.getMarque());
            carteGrise.setGenre(carteGriseDetails.getGenre());
            carteGrise.setNumeroSerie(carteGriseDetails.getNumeroSerie());
            carteGrise.setCouleur(carteGriseDetails.getCouleur());
            carteGrise.setNombrePlace(carteGriseDetails.getNombrePlace());
            carteGrise.setPuissanceFiscale(carteGriseDetails.getPuissanceFiscale());
            carteGrise.setEnergie(carteGriseDetails.getEnergie());
            carteGrise.setProprietaire(carteGriseDetails.getProprietaire());
            carteGrise.setPoidsVide(carteGriseDetails.getPoidsVide());
            carteGrise.setPoidsAutorise(carteGriseDetails.getPoidsAutorise());
            carteGrise.setDateDelivrance(carteGriseDetails.getDateDelivrance());
            carteGrise.setPhotoCarteGrise(
                    carteGriseDetails.getPhotoCarteGrise()
            );
            return carteGriseRepository.save(carteGrise);
        }).orElseThrow(() -> new RuntimeException("Carte grise non trouvée"));

        return carteGriseMapper.toCarteGriseDTO(carte);
    }

    @Override
    public void deleteCarteGrise(Long id) {
        Camion camion = camionRepository.findByCarteGriseId(id);
        if (camion!=null){
            camion.setCarteGrise(null);
            camionRepository.save(camion);
        }
        carteGriseRepository.deleteById(id); // Supprime la carte grise par son ID
    }

    @Override
    public List<CarteGrise> catreGriseExpireIn30Days() {
        LocalDate today = LocalDate.now();
        LocalDate day30 = today.plusDays(30);

        return carteGriseRepository.findCarteGriseByDateDelivranceBetween(day30, today);
    }

    @Override
    public  List<CarteGrise> catreGriseExpireBefore30Days(){
        LocalDate today = LocalDate.now();
        LocalDate day30 = today.minusDays(30);

        return carteGriseRepository.findCarteGriseByDateDelivranceBetween(today, day30);

    }



}
