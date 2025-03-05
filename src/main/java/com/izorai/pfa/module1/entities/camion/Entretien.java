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
public class Entretien implements Serializable {
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
