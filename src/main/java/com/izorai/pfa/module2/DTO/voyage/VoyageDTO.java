package com.izorai.pfa.module2.DTO.voyage;

import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.enumerations.EtatVoyage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record VoyageDTO(
        Long id,
        LocalDate dateDepart,
        LocalDate dateArrivePrevue,
        LocalDate dateArriveReelle,
        Adress lieuDepart,
        Adress lieuArrive,
        int distance,
        EtatVoyage etat,
        List<Contient> listMarchandises,
        boolean estUrgent,
        boolean estFragile,
        Camion camion,
        Chaufeur chaufeur,
        Remorque remorque
) {}