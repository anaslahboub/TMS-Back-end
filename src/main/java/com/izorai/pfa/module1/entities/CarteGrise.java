package com.izorai.pfa.module1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteGrise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateMiseEnCirculation;
    private String marque;
    private String genre;
    private Long numeroSerie;
    private String Couleur;
    private int nombrePlace;
    private String puissanceFiscale;
    private String energie;
    private String proprietaire;
    private int poidsVide;
    private int poidsAutorise;
    private LocalDate dateDelivrance;

    @OneToOne
    private Adress adress;
}
