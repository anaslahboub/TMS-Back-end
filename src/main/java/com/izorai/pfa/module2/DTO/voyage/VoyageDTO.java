package com.izorai.pfa.module2.DTO.voyage;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.DTO.camion.remorque.RemorqueDTO;
import com.izorai.pfa.module1.DTO.partenaire.chaufeur.ChaufeurRespDTO;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module2.DTO.contient.ContientDTO;
import com.izorai.pfa.module2.enumerations.EtatVoyage;

import java.time.LocalDate;
import java.util.List;

public record VoyageDTO(
        Long id,
        LocalDate dateDepart,
        LocalDate dateArrivePrevue,
        LocalDate dateArriveReelle,
        Adress lieuDepart,
        Adress lieuArrive,
        int distance,
        EtatVoyage etat,
        List<ContientDTO> listMarchandises,
        boolean estUrgent,
        boolean estFragile,
        CamionDTO camion,
        ChaufeurRespDTO chaufeur,
        RemorqueDTO remorque,
        List<String> warnings
) {}