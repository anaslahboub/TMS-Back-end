package com.izorai.pfa.module1.services.partenaire.physique;

import com.izorai.pfa.module1.DTO.partenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.DTO.partenaire.physique.PhysiqueCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.physique.PhysiqueRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.util.List;

public interface PhysiqueService {

    /// PHYSIQUE SERVICE CRUD
    public PhysiqueRespDTO addNewPhysique(PhysiqueCreateDTO physique);

    public List<PhysiqueRespDTO> getAllPhysiques();
    public PhysiqueRespDTO getPhysiqueById(Long idPartenaire); // Utilisation de idPartenaire
    public PhysiqueRespDTO updatePhysique(Long id,  PhysiqueCreateDTO physiqueDetails);
    public void deletePhysique(Long idPartenaire);
    public List<Adress> getAdressesPhysique(Long idPartenaire);

    public Adress addAdressPhysique(Long idPartenaire, AdressCreateDto adress);
}
