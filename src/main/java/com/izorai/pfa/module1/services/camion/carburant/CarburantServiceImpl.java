package com.izorai.pfa.module1.services.camion.carburant;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.DTO.camion.carburant.CarburantRespDto;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Carburant;
import com.izorai.pfa.module1.mappers.camion.CamionMapper;
import com.izorai.pfa.module1.mappers.camion.CarburantMapper;
import com.izorai.pfa.module1.mappers.camion.TypeCarburantMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.repository.camion.CarburantRepository;
import jakarta.transaction.Transactional;   
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Transactional
public class CarburantServiceImpl implements CarburantService {

    private final CarburantRepository carburantRepository;
    private final CarburantMapper carburantMapper;
    private final CamionRepository camionRepository;
    private final TypeCarburantMapper typeCarburantMapper;
    private final CamionMapper camionMapper ;




    @Override
    public CarburantRespDto createCarburant(CarburantDTO carburantDTO) {
        Carburant carburant = carburantMapper.fromCarburantDTO(carburantDTO); // Convertit le DTO en entité

        int montant = carburantDTO.getPrixParLitre()*carburantDTO.getQuantiteLitres();
        carburant.setMontantActuel(montant);
        // Récupérer l'immatriculation du camion depuis le DTO
        String immatriculation = carburantDTO.getCamion().getImmatriculation();

        // Récupérer le dernier plein du camion
        Optional<Carburant> dernierPleinOpt = carburantRepository.findLastCarburantByCamion(immatriculation);

        int distance = 0;
        if (dernierPleinOpt.isPresent()) {
            Carburant dernierPlein = dernierPleinOpt.get();
            distance = carburantDTO.getKilometrageActuel() - dernierPlein.getKilometrageActuel();
        }

        // Enregistrer la distance parcourue dans l'entité Camion
        carburant.setConsommation(distance/montant);


        carburantRepository.save(carburant);
        return carburantMapper.toCarburantRespDto(carburant); // Retourne le DTO après la sauvegarde
    }

    @Override
    public List<CarburantRespDto> getAllCarburants() {
        return carburantRepository.findAll().stream()
                .map(carburantMapper::toCarburantRespDto) // Convertit chaque Carburant en CarburantDTO
                .collect(Collectors.toList());    }

    @Override
    public Optional<CarburantRespDto> getCarburantById(Long id) {
        return carburantRepository.findById(id).map(carburantMapper::toCarburantRespDto);
    }

    @Override
    @Transactional
    public CarburantRespDto updateCarburant(Long id, CarburantDTO carburantDTO) {
        int montant = carburantDTO.getPrixParLitre()*carburantDTO.getQuantiteLitres();

        Carburant updatedCarburant = carburantRepository.findById(id).map(carburant -> {
            carburant.setCamion(carburantMapper.fromCarburantDTO(carburantDTO).getCamion());
            carburant.setTypeCarburant(carburantDTO.getTypeCarburant());
            carburant.setQuantiteLitres(carburantDTO.getQuantiteLitres());
            carburant.setMontantActuel(montant);
            carburant.setDateRemplissage(carburantDTO.getDateRemplissage());
            carburant.setKilometrageActuel(carburantDTO.getKilometrageActuel());
            carburant.setPrixParLitre(carburantDTO.getPrixParLitre());
            carburant.setPhotoCarburant(carburantDTO.getPhotoCarburant());
            carburant.setStation(carburantDTO.getStation());
            return carburantRepository.save(carburant);
        }).orElseThrow(() -> new RuntimeException("Carburant non trouvé"));

        carburantRepository.save(updatedCarburant);

        return carburantMapper.toCarburantRespDto(updatedCarburant);
    }

    @Override
    public void deleteCarburant(Long id) {
        carburantRepository.deleteById(id);
    }

    @Override
    public List<CarburantRespDto> getCarburantsByCamion(String immatriculationCamion) {
        Camion camions = camionRepository.findByImmatriculation(immatriculationCamion).get();
        return camions.getCarburants().stream().map(carburantMapper::toCarburantRespDto).collect(Collectors.toList());
    }




    @Override
    public List<CarburantRespDto> getCarburantsByDateRange(LocalDate debut, LocalDate fin) {
        return carburantRepository.findByDateRemplissageBetween(debut, fin).stream()
                .map(carburantMapper::toCarburantRespDto).collect(Collectors.toList());
    }

    @Override
    public double getConsommationMoyenneByCamion(String immatriculationCamion) {
        List<Carburant> carburants = this.getCarburantsByCamion(immatriculationCamion).stream().
                map(carburantMapper::fromCarburantRespDto).collect(Collectors.toList());
        double total = carburants.stream()
                .mapToDouble(Carburant::getConsommation)
                .sum();

        return total/carburants.size();
    }

    @Override
    public double getCoutTotalCarburant() {
        List<Carburant> carburants = this.getAllCarburants().
                stream().map(carburantMapper::fromCarburantRespDto).collect(Collectors.toList());
        return carburants.stream()
                .mapToDouble(carburant -> carburant.getMontantActuel())
                .sum();
    }

    @Override
    public double getDistanceTotalParcourue() {
        List<Carburant> carburants = carburantRepository.findAll();

        if (carburants.isEmpty()) {
            return 0;
        }

        // Trouver le kilométrage minimum et maximum
        int minKm = carburants.stream()
                .mapToInt(Carburant::getKilometrageActuel)
                .min()
                .orElse(0);

        int maxKm = carburants.stream()
                .mapToInt(Carburant::getKilometrageActuel)
                .max()
                .orElse(0);

        return maxKm - minKm;
    }

    @Transactional
    @Override
    public double getQuantityTotal() {
        return carburantRepository.sumQuantiteLitres();
    }
    @Transactional
    @Override
    public double getPrixMoyenne() {
        return carburantRepository.avgPrixParLitre().orElse(0.0);
    }

    @Override
    public double getTauxConsommationMoyenne() {
        Double moyenne = carburantRepository.findAverageConsommation();
        return moyenne != null ? moyenne : 0;
    }
}
