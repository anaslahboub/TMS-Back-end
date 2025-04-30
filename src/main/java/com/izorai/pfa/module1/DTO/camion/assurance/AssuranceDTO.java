package com.izorai.pfa.module1.DTO.camion.assurance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Data // Génère getters, setters, equals, hashCode, toString
@NoArgsConstructor // Génère un constructeur sans paramètres
@AllArgsConstructor // Génère un constructeur avec tous les paramètres
public class AssuranceDTO {
    private Long numeroContrat;
    private String company;
    private String typeCouverture;
    private int montant;
    private LocalDate dateDebut;
    private LocalDate dateExpiration;
    private int primeAnnuelle;
    private Long numCarteVerte;
    private byte[] photoAssurance;

}
