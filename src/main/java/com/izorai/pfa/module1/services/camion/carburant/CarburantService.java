package com.izorai.pfa.module1.services.camion.carburant;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.DTO.camion.carburant.CarburantRespDto;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Carburant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarburantService {
    /// Carburant sevice CRUD
    public CarburantRespDto createCarburant(CarburantDTO Carburant);
    public List<CarburantRespDto> getAllCarburants();
    public Optional<CarburantRespDto> getCarburantById(Long id);
    public CarburantRespDto updateCarburant(Long id, CarburantDTO CarburantDetails);
    public void deleteCarburant(Long id);



    // Opérations spécifiques
    List<CarburantRespDto> getCarburantsByCamion(String immatriculationCamion);
    List<CarburantRespDto> getCarburantsByDateRange(LocalDate debut, LocalDate fin);

    // Statistiques
    //Calcule la consommation moyenne en litres par 100 km du camion pour une période donnée.
    //Permet de suivre l'efficacité du camion en termes de consommation.
    double getConsommationMoyenneByCamion(String immatriculationCamion);


    //Calcule le coût total de carburant consommé par le camion sur une période donnée.
    //Utile pour estimer les coûts de transport et la gestion du budget carburant.
    double getCoutTotalCarburant();

    /**
     * retourner la distance totale parcorue
     */

    double getDistanceTotalParcourue();

    /**
     * get la quantite totale
      */

    double getQuantityTotal();

    /**
     * prix unitaire moyenne
     */
    double getPrixMoyenne();

    double getTauxConsommationMoyenne();


    public double getLastKilometrageForCamion(String immatriculation);



}
