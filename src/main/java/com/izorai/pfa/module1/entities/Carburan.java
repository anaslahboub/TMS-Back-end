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
public class Carburan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRemplissage;
    private int quantity;
    private int prixParLitre;
    private int kilometrageActuel;
    private String typeCarburant;
    @OneToOne
    private Camion camion;
}
