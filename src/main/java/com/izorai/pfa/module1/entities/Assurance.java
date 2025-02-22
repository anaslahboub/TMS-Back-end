package com.izorai.pfa.module1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroContrat;
    private String company;
    private String typeCouverture;
    private int montant;
    private LocalDate dateDebut;
    private LocalDate dateExpiration;
    private int primeAnnuelle;
    private int numCarteVerte;
    private String statutCarteVerte;

}
