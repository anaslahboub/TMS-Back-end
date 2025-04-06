package com.izorai.pfa.module2.DTO.demande;

import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module2.entities.enumeartions.StatusDemandeCotation;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeCotationDto {
    private Long id;

    private String typeRemorque;
    private String typeMarchandise;
    private String exigencesParticulieres; // Transport réfrigéré, fragile, ADR..
    private boolean transitEtranger; // true si le transport traverse une frontière
    private LocalDate dateDemande;
    private String periodeTransport; // Période souhaitée pour le transport
    private StatusDemandeCotation statut;
    private Adress adresseChargement;
    private Adress adresseDechargement;
    private Physique physique;
}
