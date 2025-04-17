package com.izorai.pfa.module2.DTO.voyage;

import com.izorai.pfa.module2.enumerations.EtatVoyage;

public record VoyageEtatDTO(
        Long id ,
        EtatVoyage etat
){
        }