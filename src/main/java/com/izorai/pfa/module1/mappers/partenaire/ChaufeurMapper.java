package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurUpdateDto;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurVoyageDTO;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChaufeurMapper {
    ChaufeurMapper INSTANCE = Mappers.getMapper(ChaufeurMapper.class);
    Chaufeur fromChaufeurCreateDTO(ChaufeurCreateDTO chaufeurCreateDTO);
    ChaufeurCreateDTO toChaufeurCreateDTO (Chaufeur chaufeur);
    Chaufeur fromChaufeurRespDTO(ChaufeurRespDTO chauffeurRespDTO);
    ChaufeurRespDTO toChaufeurRespDTO (Chaufeur chaufeur);
    ChaufeurUpdateDto fromChaufeurUpdateDto(Chaufeur chaufeurUpdateDto);
    Chaufeur toChaufeurUpdateDTO (ChaufeurUpdateDto chaufeur);


    ChaufeurVoyageDTO toChaufeurVoyageDTO(Chaufeur chaufeur) ;
    Chaufeur fromChaufeurVoyageDTO(ChaufeurVoyageDTO chaufeurVoyageDTO);
}
