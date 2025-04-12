package com.izorai.pfa.module1.entities.camion;

import com.izorai.pfa.module1.entities.partenaire.Adress;
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
public class CarteGrise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateMiseEnCirculation;
    @Column(nullable = false)
    private String marque;
    private String genre;
    @Column(nullable = false)
    private Long numeroSerie;
    private String Couleur;
    private int nombrePlace;
    private String puissanceFiscale;
    private String energie;
    private String proprietaire;
    private int poidsVide;
    private int poidsAutorise;
    private LocalDate dateDelivrance;
    @Lob
    private byte[] photoCarteGrise;

}
