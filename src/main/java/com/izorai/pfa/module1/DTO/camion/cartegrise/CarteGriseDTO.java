package com.izorai.pfa.module1.DTO.camion.cartegrise;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteGriseDTO {
    private Long id;
    private LocalDate dateMiseEnCirculation;
    private String marque;
    private String genre;
    private Long numeroSerie;
    private String couleur;
    private int nombrePlace;
    private String puissanceFiscale;
    private String energie;
    private String proprietaire;
    private int poidsVide;
    private int poidsAutorise;
    private LocalDate dateDelivrance;

    private byte[] photoCarteGrise;



}