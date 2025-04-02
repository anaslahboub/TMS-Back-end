package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.entities.camion.CarteGrise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarteGriseMapper {
    CarteGriseMapper INSTANCE = Mappers.getMapper(CarteGriseMapper.class);
    CarteGrise fromCarteGriseDTO(CarteGriseDTO carteGriseDTO);

    @Mapping(target = "photoCarteGrise", source = "photoCarteGrise")
    CarteGriseDTO toCarteGriseDTO(CarteGrise carteGrise);
}
