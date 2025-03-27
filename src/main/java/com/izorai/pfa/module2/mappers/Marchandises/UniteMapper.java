package com.izorai.pfa.module2.mappers.Marchandises;

import com.izorai.pfa.module2.DTO.marchandises.UniteDTO;
import com.izorai.pfa.module2.entities.marchandises.Unite;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UniteMapper {
    UniteMapper INSTANCE = Mappers.getMapper(UniteMapper.class);

    UniteDTO toDto(Unite unite);
    Unite toEntity(UniteDTO uniteDTO);
}