package com.izorai.pfa.module1.services.camion.camion;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.DTO.camion.camion.CamionRespDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CamionService {

    ///camion service
    public CamionDTO addNewCamion(CamionDTO camionDTO);
    public List<CamionDTO> getAllCamions();
    public Optional<CamionRespDto> getCamionById(String immatriculation);
    public CamionDTO updateCamion(String immatriculation, CamionDTO camionDetails);
    public void deleteCamion(String immatriculation);


    // Statistiques
    int getNombreCamionsActifs();
    int getNombreCamionsEnMaintenance();


    Map<String, Double> getCoutsTotauxParCamion(LocalDate debut, LocalDate fin);
    Map<String, Double> getCoutsCarburantParCamion(LocalDate debut, LocalDate fin);
    Map<String, Double> getCoutsEntretienParCamion(LocalDate debut, LocalDate fin);

    // Performance
    Map<String, Double> getConsommationMoyenneParCamion();
    Map<String, Integer> getKilometragesParcourusParCamion(LocalDate debut, LocalDate fin);













}
