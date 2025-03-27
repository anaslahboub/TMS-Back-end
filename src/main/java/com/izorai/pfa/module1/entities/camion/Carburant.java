package com.izorai.pfa.module1.entities.camion;

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
public class  Carburant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRemplissage;
    private int quantiteLitres;
    private int prixParLitre;
    private int kilometrageActuel;
    @ManyToOne
    private TypeCarburant typeCarburant;
    @ManyToOne
    private Camion camion;
}
