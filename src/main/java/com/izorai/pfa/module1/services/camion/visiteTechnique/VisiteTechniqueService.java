package com.izorai.pfa.module1.services.camion.visiteTechnique;


import com.izorai.pfa.module1.DTO.camion.visiteTechnique.VisiteTechniqueDto;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;

import java.util.List;

public interface VisiteTechniqueService {
    VisiteTechniqueDto save(VisiteTechnique visiteTechnique);
    VisiteTechniqueDto getById(Long id);
    List<VisiteTechniqueDto> getAll();
    List<VisiteTechniqueDto> getByCamion(String immatriculation);
    void delete(Long id);


    // New methods
    VisiteTechniqueDto update(Long id, VisiteTechnique visiteTechnique);
//    boolean hasValidVisiteTechnique(String immatriculation);
//    boolean isCamionEligibleForTransport(String immatriculation);
//    List<VisiteTechnique> getVisitesExpiringSoon(int days);
//    List<VisiteTechnique> getVisitesWithIssues(String immatriculation);


    public List<VisiteTechnique> getVisitesTechniquesExpirantDans30Jours() ;
    public List<VisiteTechnique> getVisitesTechniquesExpirantBefore30Jours() ;

}
