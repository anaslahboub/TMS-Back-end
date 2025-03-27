package com.izorai.pfa.module2.mappers.Marchandises;

import com.izorai.pfa.module2.DTO.marchandises.CategorieDTO;
import com.izorai.pfa.module2.entities.marchandises.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);

    CategorieDTO toDto(Categorie categorie);
    Categorie toEntity(CategorieDTO categorieDTO);
}