package com.izorai.pfa.module2.mappers;

import com.izorai.pfa.module2.DTO.demande.DemandeCotationDto;
import com.izorai.pfa.module2.entities.DemandeCotation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DemandeCotationMapper {
    DemandeCotationMapper INSTANCE = Mappers.getMapper(DemandeCotationMapper.class);
    DemandeCotationDto toDemandeCotationDto(DemandeCotation demandCotation);
    DemandeCotation fromDemandeCotationDto(DemandeCotationDto demandeCotationDto);
    @Mapping(target = "id", ignore = true)
    void updateFromDto(DemandeCotationDto dto, @MappingTarget DemandeCotation entity);
}
