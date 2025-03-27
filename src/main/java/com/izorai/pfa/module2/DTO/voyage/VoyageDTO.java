package com.izorai.pfa.module2.DTO.voyage;

import java.time.LocalDateTime;

public record VoyageDTO(
        Long id,
        String reference,
        LocalDateTime dateDepart,
        LocalDateTime dateArriveePrevue,
        LocalDateTime dateArriveeReelle,
        String lieuDepart,
        String lieuArrivee,
        String statut,
        String commentaire
) {}