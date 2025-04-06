package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.DTO.camion.entretien.EntretienViewResp;
import com.izorai.pfa.module1.entities.camion.CarteGrise;
import com.izorai.pfa.module1.entities.camion.Entretien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface EntretienMapper {
    EntretienMapper INSTANCE = Mappers.getMapper(EntretienMapper.class);
    @Mapping(source = "statusEntretien", target = "statusEntretien")
    EntretienDTO toEntretienDTO(Entretien entretien);
    @Mapping(source = "statusEntretien", target = "statusEntretien")
    Entretien fromEntretienDTO(EntretienDTO entretienDTO);
    @Mapping(source = "statusEntretien", target = "statusEntretien")
    EntretienViewResp toEntretienViewResp(Entretien entretien);
    Entretien fromEntretienViewResp(EntretienViewResp entretienViewResp);
}