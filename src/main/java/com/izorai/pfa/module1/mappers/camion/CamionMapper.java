package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CamionMapper {
    CamionMapper INSTANCE = Mappers.getMapper(CamionMapper.class);
    CamionDTO toCamionDto(Camion camion);
    Camion fromCamionDTO(CamionDTO camionDTO);
}
