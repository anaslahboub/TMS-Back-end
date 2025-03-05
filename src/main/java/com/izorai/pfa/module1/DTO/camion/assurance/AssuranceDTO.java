package com.izorai.pfa.module1.DTO.camion.assurance;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;

import java.time.LocalDate;

public record AssuranceDTO(
         Long id,
         String compagnie,
         LocalDate dateDebut,
         LocalDate dateFin
) {
}
