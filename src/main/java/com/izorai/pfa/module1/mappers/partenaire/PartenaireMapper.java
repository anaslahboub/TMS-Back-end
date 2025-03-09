package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Partenaire;
import com.izorai.pfa.module1.DTO.paretenaire.paretenaire.PartenaireCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartenaireMapper {
    PartenaireMapper INSTANCE = Mappers.getMapper(PartenaireMapper.class);
    Partenaire fromPartenaireCreateDTO(PartenaireCreateDTO dto);
    PartenaireCreateDTO toPartenaireCreateDTO(Partenaire partenaire);
    Partenaire fromPartenaireRespDTO(PartenaireRespDTO dto);
    PartenaireRespDTO toPartenaireRespDTO(Partenaire partenaire);

}
