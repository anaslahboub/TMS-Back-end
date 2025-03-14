package com.izorai.pfa.module1.services.partenaire.typepartenaire;

import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireNomDto;
import com.izorai.pfa.module1.DTO.paretenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;

import java.util.List;

public interface TypePartenaireService {
    TypePartenaireRespDTO addNewTypePartenaire(TypePartenaireCreateDTO typePartenaire);
    List<TypePartenaireRespDTO> getAllTypePartenaires();
    TypePartenaireRespDTO getTypePartenaireById(Long idTypePartenaire);
    TypePartenaireRespDTO updateTypePartenaire(Long id ,TypePartenaireCreateDTO typePartenaireDetails);
    void deleteTypePartenaire(Long idTypePartenaire);
    List<TypePartenaireNomDto> getAllTypePartenaireNoms();
    TypePartenaire getPartenaireByLibelle(String nom);

}
