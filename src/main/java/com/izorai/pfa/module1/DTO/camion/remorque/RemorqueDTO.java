package com.izorai.pfa.module1.DTO.camion.remorque;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;

public record RemorqueDTO(
        Long immatriculation,
        CarteGriseDTO carteGrise,
        AssuranceDTO assurance,
        String typeRemorque,
        int volumesStockage,
        int poidsVide,
        int poidsChargeMax
) {
}
