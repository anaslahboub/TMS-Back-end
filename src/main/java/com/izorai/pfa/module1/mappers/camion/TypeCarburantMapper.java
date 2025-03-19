package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.typeCarburant.TypeCarburantDTO;
import com.izorai.pfa.module1.entities.camion.TypeCarburant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TypeCarburantMapper {
    TypeCarburantMapper INSTANCE = Mappers.getMapper(TypeCarburantMapper.class);
    TypeCarburant fromTypeCarburantDTO(TypeCarburantDTO TypeTypeCarburantDTO);
    TypeCarburantDTO toTypeCarburantDTO(TypeCarburant TypeCarburant);
}
