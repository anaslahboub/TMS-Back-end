package com.izorai.pfa.module1.services.partenaire.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Partenaire;

import java.util.List;

public interface PartenaireService {
    PartenaireCreateDTO createPartenaire(PartenaireCreateDTO partenaire);
    List<PartenaireRespDTO> getAllPartenaires();
    PartenaireRespDTO getPartenaireById(Long idPartenaire);
    Partenaire updatePartenaire(Partenaire partenaireDetails);
    void deletePartenaire(Long idPartenaire);

}
