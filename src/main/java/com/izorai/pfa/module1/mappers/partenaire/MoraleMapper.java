package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.partenaire.Morale.MoraleCreateDTO;
import com.izorai.pfa.module1.DTO.partenaire.Morale.MoraleRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Morale;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MoraleMapper {
    MoraleMapper INSTANCE = Mappers.getMapper(MoraleMapper.class);

    Morale fromMoraleCreateDTO(MoraleCreateDTO moraleCreateDTO);
    MoraleCreateDTO toMoraleCreateDTO(Morale morale);
    Morale fromMoraleRespDTO(MoraleRespDTO moraleCreateDTO);
    MoraleRespDTO toMoraleRespDTO(Morale morale);

}
