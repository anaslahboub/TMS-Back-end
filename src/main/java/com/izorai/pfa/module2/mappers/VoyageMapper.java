package com.izorai.pfa.module2.mappers;

import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.entities.Voyage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoyageMapper {
    VoyageMapper INSTANCE = Mappers.getMapper(VoyageMapper.class);

    VoyageDTO toDto(Voyage voyage);
    Voyage toEntity(VoyageDTO voyageDTO);
}