package com.izorai.pfa.module2.mappers;

import com.izorai.pfa.module2.DTO.voyage.VoyageArriveeDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageEtatDTO;
import com.izorai.pfa.module2.entities.Voyage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VoyageMapper {
    VoyageDTO toDto(Voyage voyage);
    Voyage toEntity(VoyageDTO voyageDTO);
    @Mapping(target = "id", ignore = true)
    void updateFromDto(VoyageDTO dto, @MappingTarget Voyage entity);

    Voyage fromVoyageEtatDTO(VoyageEtatDTO voyageEtatDTO);
    VoyageEtatDTO toVoyageEtatDTO(Voyage voyage);

    Voyage fromVoyageArriveeDTO(VoyageArriveeDTO voyageArriveeDTO);
    VoyageArriveeDTO toVoyageArriveeDTO(Voyage voyage);
}