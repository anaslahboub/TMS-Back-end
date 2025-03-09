package com.izorai.pfa.module2.entities;


import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module2.entities.enumeartions.StatusDemandeCotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeCotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeRemorque;
    private String typeMarchandise;
    private String exigencesParticulieres; // Transport réfrigéré, fragile, ADR..
    private boolean transitEtranger; // true si le transport traverse une frontière
    private LocalDate dateDemande;
    private LocalDate periodeTransport; // Période souhaitée pour le transport
    private StatusDemandeCotation statut;
    @OneToOne
    private Adress adresseChargement;
    @OneToOne
    private Adress adresseDechargement;
    @OneToOne
    private Physique physique;
}