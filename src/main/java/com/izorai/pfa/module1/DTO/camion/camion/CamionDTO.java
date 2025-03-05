package com.izorai.pfa.module1.DTO.camion.camion;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;

public record CamionDTO(
        Long immatriculation,
        CarteGriseDTO carteGrise,
        AssuranceDTO assurance
) {
}
