package com.izorai.pfa.module1.DTO.camion.remorque;

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
