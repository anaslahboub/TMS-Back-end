package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.entretien.EntretienDTO;
import com.izorai.pfa.module1.entities.camion.CarteGrise;
import com.izorai.pfa.module1.entities.camion.Entretien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface EntretienMapper {
    EntretienMapper INSTANCE = Mappers.getMapper(EntretienMapper.class);
    @Mapping(source = "camion.immatriculation", target = "immatriculation")
    EntretienDTO toEntretienDTO(Entretien entretien);

    @Mapping(source = "immatriculation", target = "camion.immatriculation")
    Entretien fromEntretienDTO(EntretienDTO entretienDTO);
}
