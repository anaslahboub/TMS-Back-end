package com.izorai.pfa.module1.services.camion.carburant;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Carburant;
import com.izorai.pfa.module1.mappers.camion.CamionMapper;
import com.izorai.pfa.module1.mappers.camion.CarburantMapper;
import com.izorai.pfa.module1.mappers.camion.TypeCarburantMapper;
import com.izorai.pfa.module1.repository.camion.CamionRepository;
import com.izorai.pfa.module1.repository.camion.CarburantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
@AllArgsConstructor
public class CarburantServiceImpl implements CarburantService {

    private final CarburantRepository carburantRepository;
    private final CarburantMapper carburantMapper;
    private final CamionRepository camionRepository;
    private final TypeCarburantMapper typeCarburantMapper;
    private final CamionMapper camionMapper ;




    @Override
    public CarburantDTO createCarburant(CarburantDTO carburantDTO) {
        Carburant carburant = carburantMapper.fromCarburantDTO(carburantDTO); // Convertit le DTO en entité
        carburantRepository.save(carburant);
        return carburantMapper.toCarburantDTO(carburant); // Retourne le DTO après la sauvegarde
    }

    @Override
    public List<CarburantDTO> getAllCarburants() {
        return carburantRepository.findAll().stream()
                .map(carburantMapper::toCarburantDTO) // Convertit chaque Carburant en CarburantDTO
                .collect(Collectors.toList());    }

    @Override
    public Optional<CarburantDTO> getCarburantById(Long id) {
        return carburantRepository.findById(id).map(carburantMapper::toCarburantDTO);
    }

    @Override
    public CarburantDTO updateCarburant(Long id, CarburantDTO carburantDTO) {
        Carburant updatedCarburant = carburantRepository.findById(id).map(carburant -> {
            carburant.setCamion(carburantMapper.fromCarburantDTO(carburantDTO).getCamion());
            carburant.setTypeCarburant(typeCarburantMapper.fromTypeCarburantDTO(carburantDTO.typeCarburant()));
            carburant.setQuantiteLitres(carburantDTO.quantity());
            carburant.setDateRemplissage(carburantDTO.dateRemplissage());
            carburant.setKilometrageActuel(carburantDTO.kilometrageActuel());
            carburant.setPrixParLitre(carburantDTO.prixParLitre());
            carburant.setCamion(camionMapper.fromCamionDTO(carburantDTO.camion()));
            return carburantRepository.save(carburant);
        }).orElseThrow(() -> new RuntimeException("Carburant non trouvé"));

        return carburantMapper.toCarburantDTO(updatedCarburant);
    }

    @Override
    public void deleteCarburant(Long id) {
        carburantRepository.deleteById(id);
    }

    @Override
    public List<CarburantDTO> getCarburantsByCamion(String immatriculationCamion) {
        Camion camions = camionRepository.findByImmatriculation(immatriculationCamion).get();
        return camions.getCarburants().stream().map(carburantMapper::toCarburantDTO).collect(Collectors.toList());
    }




    @Override
    public List<CarburantDTO> getCarburantsByDateRange(LocalDate debut, LocalDate fin) {
        return carburantRepository.findByDateRemplissageBetween(debut, fin).stream()
                .map(carburantMapper::toCarburantDTO).collect(Collectors.toList());
    }

    @Override
    public double getConsommationMoyenneByCamion(String immatriculationCamion) {
        List<Carburant> carburants = this.getCarburantsByCamion(immatriculationCamion).stream().
                map(carburantMapper::fromCarburantDTO).collect(Collectors.toList());
        double totalKilometrage = 0;
        double totalCarburant = 0;

        for (Carburant carburant : carburants) {
            totalKilometrage += carburant.getKilometrageActuel();
            totalCarburant += carburant.getQuantiteLitres();
        }

        if (totalKilometrage == 0) return 0;
        return (totalCarburant / totalKilometrage) * 100;  // Litres par 100 km
    }

    @Override
    public double getCoutTotalCarburant(String immatriculationCamion) {
        List<Carburant> carburants = this.getCarburantsByCamion(immatriculationCamion).
                stream().map(carburantMapper::fromCarburantDTO).collect(Collectors.toList());
        return carburants.stream()
                .mapToDouble(carburant -> carburant.getQuantiteLitres() * carburant.getPrixParLitre())
                .sum();
    }
}
