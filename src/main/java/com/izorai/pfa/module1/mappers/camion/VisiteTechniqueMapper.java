package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.visiteTechnique.VisiteTechniqueDto;
import com.izorai.pfa.module1.entities.camion.VisiteTechnique;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisiteTechniqueMapper {
    VisiteTechniqueMapper INSTANCE = Mappers.getMapper(VisiteTechniqueMapper.class);

    VisiteTechniqueDto toDto(VisiteTechnique visiteTechnique);
    VisiteTechnique fromDto(VisiteTechniqueDto visiteTechniqueDto);

}
