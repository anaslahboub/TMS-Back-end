package com.izorai.pfa.module1.DTO.camion.remorque;

import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.DTO.camion.typeRemorque.TypeRemorqueDTO;

public record RemorqueDTO(
        Long idRemorque,
        TypeRemorqueDTO typeRemorque,
        int volumeStockage,
        int poidsVide,
        int poidsChargeMax,
        boolean disponible
) {
}
