package com.izorai.pfa.module1.entities;

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
public class Kilometrage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateEnregistrement;
    private int kilometrageActuel;
    private LocalDate dernierEntretien;
    private LocalDate prochainEntretien;
    private String notes;
    @OneToOne
    private Camion camion;

}
