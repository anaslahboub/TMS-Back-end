package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageEtatDTO;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.enumerations.EtatVoyage;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VoyageService {
    // CRUD Operations
    VoyageDTO createVoyage(VoyageDTO voyageDTO);
    VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO);
    void deleteVoyage(Long id);
    Optional<VoyageDTO> getVoyageById(Long id);
    List<VoyageDTO> getAllVoyages();

    // Status Management
    VoyageDTO updateStatus(Long id, EtatVoyage newStatus);
    List<VoyageDTO> getVoyagesByStatus(EtatVoyage status);

    // Search & Filtering
    List<VoyageDTO> getVoyagesByDateRange(String startDate, String endDate);

    // Relationship Management
    VoyageDTO assignChauffeur(Long voyageId, Long chauffeurId);
    VoyageDTO assignCamion(Long voyageId, Long camionId);
    VoyageDTO assignRemorque(Long voyageId, Long remorqueId);
    VoyageDTO addMarchandise(Long voyageId, Contient contient);

    // Statistics & Reporting
    long countVoyagesByStatus(EtatVoyage status);
    Map<EtatVoyage, Long> getVoyagesStatistics();


    VoyageEtatDTO changeVoyageEtat(VoyageEtatDTO voyageEtatDTO);

    VoyageDTO checkVoyageWarnings(Long voyageId);
    List<Voyage> getVoaygeInLast30Days();
}