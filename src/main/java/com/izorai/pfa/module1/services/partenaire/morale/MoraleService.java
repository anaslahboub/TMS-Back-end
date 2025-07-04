package com.izorai.pfa.module1.services.partenaire.morale;

import com.izorai.pfa.module1.DTO.partenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.util.List;

public interface MoraleService {
    /// MORALE SERVICE CRUD
    public MoraleRespDTO addNewMorale(MoraleCreateDTO morale);
    public List<MoraleRespDTO> getAllMorales();
    public MoraleRespDTO getMoraleById(Long idPartenaire); // Utilisation de idPartenaire
    public MoraleRespDTO updateMorale(Long id,MoraleCreateDTO moraleDetails);
    public void deleteMorale(Long idPartenaire);
    public List<Adress> getAdressesMorale(Long idPartenaire);

}
