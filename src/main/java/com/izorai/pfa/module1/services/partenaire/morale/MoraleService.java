package com.izorai.pfa.module1.services.partenaire.morale;

import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.Morale.MoraleRespDTO;

import java.util.List;

public interface MoraleService {
    /// MORALE SERVICE CRUD
    public MoraleRespDTO addNewMorale(MoraleCreateDTO morale);
    public List<MoraleRespDTO> getAllMorales();
    public MoraleRespDTO getMoraleById(Long idPartenaire); // Utilisation de idPartenaire
    public MoraleRespDTO updateMorale(Long id,MoraleCreateDTO moraleDetails);
    public void deleteMorale(Long idPartenaire);
}
