package com.izorai.pfa.module1.services.partenaire.physique;

import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.physique.PhysiqueRespDTO;

import java.util.List;

public interface PhysiqueService {

    /// PHYSIQUE SERVICE CRUD
    public PhysiqueRespDTO addNewPhysique(PhysiqueCreateDTO physique);
    public List<PhysiqueRespDTO> getAllPhysiques();
    public PhysiqueRespDTO getPhysiqueById(Long idPartenaire); // Utilisation de idPartenaire
    public PhysiqueRespDTO updatePhysique(Long id,  PhysiqueCreateDTO physiqueDetails);
    public void deletePhysique(Long idPartenaire);
}
