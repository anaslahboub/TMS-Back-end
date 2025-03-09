package com.izorai.pfa.module1.DTO.camion.assurance;

import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import lombok.*;

import java.time.LocalDate;

//@Data // Génère getters, setters, equals, hashCode, toString
//@NoArgsConstructor // Génère un constructeur sans paramètres
//@AllArgsConstructor // Génère un constructeur avec tous les paramètres
//public class AssuranceDTO {
//    private Long numeroContrat;
//    private String company;
//    private String typeCouverture;
//    private int montant;
//    private LocalDate dateDebut;
//    private LocalDate dateExpiration;
//    private int primeAnnuelle;
//    private int numCarteVerte;
//    private String statutCarteVerte;
//}
public record AssuranceDTO(
        Long numeroContrat,
        String company,
        String typeCouverture,
        int montant,
        LocalDate dateDebut,
        LocalDate dateExpiration,
        int primeAnnuelle,
        Long numCarteVerte
) {}
