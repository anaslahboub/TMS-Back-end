package com.izorai.pfa.module2.services;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VoyageService {
    VoyageDTO createVoyage(VoyageDTO voyageDTO);
    List<VoyageDTO> getAllVoyages();
    Optional<VoyageDTO> getVoyageById(Long id);
    List<VoyageDTO> getVoyagesByDateRange(LocalDateTime start, LocalDateTime end);
    List<VoyageDTO> getVoyagesByStatut(String statut);
    VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO);
    void deleteVoyage(Long id);
}