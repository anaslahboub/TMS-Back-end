package com.izorai.pfa.module2.mappers.Marchandises;

import com.izorai.pfa.module2.DTO.marchandises.MarchandiseDTO;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MarchandiseMapper {
    MarchandiseMapper INSTANCE = Mappers.getMapper(MarchandiseMapper.class);

    MarchandiseDTO toDto(Marchandise marchandise);
    Marchandise toEntity(MarchandiseDTO marchandiseDTO);
}