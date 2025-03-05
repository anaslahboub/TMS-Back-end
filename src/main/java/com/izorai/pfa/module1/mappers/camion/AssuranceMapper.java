package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.entities.camion.Assurance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssuranceMapper {
    AssuranceMapper INSTANCE = Mappers.getMapper(AssuranceMapper.class);
    AssuranceDTO toAssuranceDto(Assurance assurance);
    Assurance froAssuranceDto(AssuranceDTO assuranceDTO);
}
