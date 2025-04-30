package com.izorai.pfa.module2.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Voyage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDepart;
    private LocalDate dateArrivePrevue;
    private LocalDate dateArriveReelle;

    @OneToOne(cascade = CascadeType.ALL)  // Changé de REMOVE à ALL
    private Adress lieuDepart;

    @OneToOne(cascade = CascadeType.ALL)  // Changé de REMOVE à ALL
    private Adress lieuArrive;
    private int distance;

    @Enumerated(EnumType.STRING)
    private EtatVoyage etat;
    private boolean estUrgent;
    private boolean estFragile;

    @ManyToOne
    private Chaufeur chaufeur;

    @ManyToOne
    private Remorque remorque;

    @ManyToOne
    private Camion camion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contient> listMarchandises = new ArrayList<>();

    // Add to Voyage entity
    @ElementCollection
    private List<String> warnings = new ArrayList<>();

    // Add this method to Voyage entity
    public void checkForWarnings() {
        this.warnings.clear();

        LocalDate voyageEnd = this.dateArrivePrevue != null ?
                this.dateArrivePrevue : this.dateDepart.plusDays(1);

        // Check assurance expiration
        if (this.camion != null && this.camion.getAssurance() != null) {
            LocalDate assuranceExpiry = this.camion.getAssurance().getDateExpiration();
            if (assuranceExpiry.isBefore(voyageEnd)) {
                warnings.add("Assurance expires on " + assuranceExpiry);
            }
        }

        // Check carte grise 10-year limit
        if (this.camion != null && this.camion.getCarteGrise() != null) {
            LocalDate carteGriseExpiry = this.camion.getCarteGrise()
                    .getDateMiseEnCirculation().plusYears(10);
            if (carteGriseExpiry.isBefore(voyageEnd)) {
                warnings.add("Carte grise exceeds 10 years on " + carteGriseExpiry);
            }
        }

        // Check driver's license
        if (this.chaufeur != null) {
            LocalDate permisExpiry = this.chaufeur.getDateExpirationPermis();
            if (permisExpiry.isBefore(voyageEnd)) {
                warnings.add("Driver license expires on " + permisExpiry);
            }
        }
    }
}