package com.izorai.pfa.module1.DTO.camion.cartegrise;

import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.time.LocalDate;

public record CarteGriseDTO (
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
){

}
