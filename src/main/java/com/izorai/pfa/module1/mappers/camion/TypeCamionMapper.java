package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;
import com.izorai.pfa.module1.entities.camion.TypeCamion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TypeCamionMapper {
    TypeCamionMapper INSTANCE = Mappers.getMapper(TypeCamionMapper.class);

    TypeCamion fromTypeCamionDTO(TypeCamionDTO typeCamionDTO); // Converts DTO to Entity
    TypeCamionDTO toTypeCamionDTO(TypeCamion typeCamion); // Converts Entity to DTO
}