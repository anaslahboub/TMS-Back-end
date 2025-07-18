package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireNomDto;
import com.izorai.pfa.module1.DTO.partenaire.typePartenaire.TypePartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TypePartenaireMapper {
    TypePartenaireMapper INSTANCE = Mappers.getMapper(TypePartenaireMapper.class);
    TypePartenaire fromTypePartenaireCreateDTO(TypePartenaireCreateDTO typePartenaireCreateDTO);
    TypePartenaireCreateDTO toTypePartenaireCreateDTO(TypePartenaire typePartenaire);
    TypePartenaire fromTypePartenaireRespDTO(TypePartenaireRespDTO typePartenaireRespDTO);
    TypePartenaireRespDTO toTypePartenaireRespDTO(TypePartenaire typePartenaire);

    TypePartenaireNomDto fromEntityTypePartenaire(TypePartenaire entityTypePartenaire);
    TypePartenaire toEntityTypePartenaire(TypePartenaireNomDto typePartenaireNomDto);

}
