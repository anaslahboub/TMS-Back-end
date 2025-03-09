package com.izorai.pfa.module1.DTO.camion.cartegrise;



import com.izorai.pfa.module1.entities.partenaire.Adress;
import java.time.LocalDate;

public record CarteGriseDTO(
        Long id,
        LocalDate dateMiseEnCirculation,
        String marque,
        String genre,
        Long numeroSerie,
        String couleur,
        int nombrePlace,
        String puissanceFiscale,
        String energie,
        String proprietaire,
        int poidsVide,
        int poidsAutorise,
        LocalDate dateDelivrance,
        Adress adress
) {}



//import com.izorai.pfa.module1.entities.partenaire.Adress;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CarteGriseDTO {
//    private Long id;
//    private LocalDate dateMiseEnCirculation;
//    private String marque;
//    private String genre;
//    private Long numeroSerie;
//    private String couleur;
//    private int nombrePlace;
//    private String puissanceFiscale;
//    private String energie;
//    private String proprietaire;
//    private int poidsVide;
//    private int poidsAutorise;
//    private LocalDate dateDelivrance;
//    private Adress adress;
//}