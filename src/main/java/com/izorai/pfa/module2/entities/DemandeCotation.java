package com.izorai.pfa.module2.entities;


import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.Physique;
import com.izorai.pfa.module2.enumerations.StatusDemandeCotation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeCotation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeRemorque;
    private String typeMarchandise;
    private String exigencesParticulieres; // Transport réfrigéré, fragile, ADR..
    private boolean transitEtranger; // true si le transport traverse une frontière
    private LocalDate dateDemande;
    private String periodeTransport; // Période souhaitée pour le transport
    private StatusDemandeCotation statut;
    @OneToOne(cascade = CascadeType.ALL)
    private Adress adresseChargement;
    @OneToOne(cascade = CascadeType.ALL)
    private Adress adresseDechargement;
    @OneToOne(cascade = CascadeType.ALL)
    private Physique physique;
}