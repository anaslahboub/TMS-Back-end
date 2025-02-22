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
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateEntretien;
    private String typeEntretien;
    private String description;
    private int cout;
    private LocalDate dateProchainEntretien;
    @OneToOne
    private Camion camion;
}
