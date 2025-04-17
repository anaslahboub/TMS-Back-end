package com.izorai.pfa.module2.mappers;

import com.izorai.pfa.module2.DTO.voyage.VoyageArriveeDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageDTO;
import com.izorai.pfa.module2.DTO.voyage.VoyageEtatDTO;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.contient.Contient;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VoyageMapper {
    VoyageDTO toDto(Voyage voyage);
    Voyage toEntity(VoyageDTO voyageDTO);


    Voyage fromVoyageEtatDTO(VoyageEtatDTO voyageEtatDTO);
    VoyageEtatDTO toVoyageEtatDTO(Voyage voyage);

    Voyage fromVoyageArriveeDTO(VoyageArriveeDTO voyageArriveeDTO);
    VoyageArriveeDTO toVoyageArriveeDTO(Voyage voyage);


}