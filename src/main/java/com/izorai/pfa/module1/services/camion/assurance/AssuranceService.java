package com.izorai.pfa.module1.services.camion.assurance;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;

import java.time.LocalDate;
import java.util.List;

public interface AssuranceService {
    public AssuranceDTO addNewAssurance(AssuranceDTO assurance);
    public List<AssuranceDTO> getAllAssurances();
    public AssuranceDTO getAssuranceById(Long numeroContrat);
    public AssuranceDTO updateAssurance(Long numeroContrat, AssuranceDTO assuranceDetails);
    public void deleteAssurance(Long numeroContrat);


    // Recherche par camion
    AssuranceDTO getAssuranceByCamion(String immatriculationCamion);

    // Renouvellement d'une assurance
    AssuranceDTO renouvelerAssurance(Long id, LocalDate nouvelleDate);

    // Vérification des assurances expirées
    void checkExpirationAssurances();

    // Calcul du coût total des assurances
    double getTotalPrimesAnnuelles();


    public List<AssuranceDTO> getAssurancesExpirantDans30Jours();
}
