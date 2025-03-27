package com.izorai.pfa.module2.mappers.Marchandises;

import com.izorai.pfa.module2.DTO.marchandises.EmballageDTO;
import com.izorai.pfa.module2.entities.marchandises.Emballage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmballageMapper {
    EmballageMapper INSTANCE = Mappers.getMapper(EmballageMapper.class);

    EmballageDTO toDto(Emballage emballage);
    Emballage toEntity(EmballageDTO emballageDTO);
}