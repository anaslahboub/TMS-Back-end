package com.izorai.pfa.module1.services.camion.carburant;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.entities.camion.Carburant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarburantService {
    /// Carburant sevice CRUD
    public CarburantDTO createCarburant(CarburantDTO Carburant);
    public List<CarburantDTO> getAllCarburants();
    public Optional<CarburantDTO> getCarburantById(Long id);
    public CarburantDTO updateCarburant(Long id, CarburantDTO CarburantDetails);
    public void deleteCarburant(Long id);



    // Opérations spécifiques
    List<CarburantDTO> getCarburantsByCamion(String immatriculationCamion);
    List<CarburantDTO> getCarburantsByDateRange(LocalDate debut, LocalDate fin);

    // Statistiques
    //Calcule la consommation moyenne en litres par 100 km du camion pour une période donnée.
    //Permet de suivre l'efficacité du camion en termes de consommation.
    double getConsommationMoyenneByCamion(String immatriculationCamion);


    //Calcule le coût total de carburant consommé par le camion sur une période donnée.
    //Utile pour estimer les coûts de transport et la gestion du budget carburant.
    double getCoutTotalCarburant(String immatriculationCamion);


}
