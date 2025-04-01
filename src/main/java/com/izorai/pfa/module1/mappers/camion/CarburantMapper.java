package com.izorai.pfa.module1.mappers.camion;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.DTO.camion.carburant.CarburantRespDto;
import com.izorai.pfa.module1.entities.camion.Carburant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarburantMapper {
    CarburantMapper INSTANCE = Mappers.getMapper(CarburantMapper.class);
    Carburant fromCarburantDTO(CarburantDTO carburantDTO);
    CarburantDTO toCarburantDTO(Carburant carburant);
    CarburantRespDto toCarburantRespDto(Carburant carburant);
    Carburant fromCarburantRespDto(CarburantRespDto carburantRespDto);

}
