package com.izorai.pfa.module2.services.demande;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module2.DTO.demande.DemandeCotationDto;
import com.izorai.pfa.module2.enumerations.StatusDemandeCotation;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DemandeCotationService {
    // CRUD Operations
    DemandeCotationDto createDemande(DemandeCotationDto demande);
    DemandeCotationDto updateDemande(Long id, DemandeCotationDto demande);
    void deleteDemande(Long id);
    Optional<DemandeCotationDto> getDemandeById(Long id);
    List<DemandeCotationDto> getAllDemandes();

    // Status Management
    DemandeCotationDto updateStatus(Long id, StatusDemandeCotation newStatus);
    List<DemandeCotationDto> getDemandesByStatus(StatusDemandeCotation status);
    // Search & Filtering
    List<DemandeCotationDto> searchByTypeMarchandise(String type);



    // Statistics & Reporting
    long countDemandesByStatus(StatusDemandeCotation status);
    Map<StatusDemandeCotation, Long> getDemandesStatistics();

    // Relationship Management
    DemandeCotationDto assignPhysique(Long demandeId, Long physiqueId);
    DemandeCotationDto updateChargementAddress(Long demandeId, Adress newAddress);
    DemandeCotationDto   updateDechargementAddress(Long demandeId, Adress newAddress);
}
