package com.izorai.pfa.module2.DTO.contient;


import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;

public record ContientDTO(
        Long id,
        Voyage voyage,
        Marchandise marchandise,
        long quantite
) {}