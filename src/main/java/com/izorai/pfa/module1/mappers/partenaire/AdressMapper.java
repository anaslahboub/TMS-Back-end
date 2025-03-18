package com.izorai.pfa.module1.mappers.partenaire;

import com.izorai.pfa.module1.DTO.paretenaire.adress.AdressCreateDto;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface AdressMapper {
    AdressMapper INSTANCE = Mappers.getMapper(AdressMapper.class);
    AdressCreateDto toAdressCreateDto(Adress adress);
    Adress fromAdressCreateDto(AdressCreateDto adressCreateDto);


}
