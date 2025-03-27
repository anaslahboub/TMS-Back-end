package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.partenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdressMapper {
    AdressMapper INSTANCE = Mappers.getMapper(AdressMapper.class);
    AdressCreateDto toAdressCreateDto(Adress adress);
    Adress fromAdressCreateDto(AdressCreateDto adressCreateDto);


}
