package com.izorai.pfa.module1.entities.camion;

import com.izorai.pfa.module1.entities.enumerations.StatusEntretien;
import com.izorai.pfa.module1.entities.enumerations.TypeEntretien;
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
    @Column(nullable = false)
    private LocalDate dateEntretien;
    @Enumerated(EnumType.STRING)
    private TypeEntretien typeEntretien;
    private String description;
    @Column(nullable = false)
    private int cout;
    private LocalDate dateProchainEntretien;
    @Enumerated(EnumType.STRING)
    private StatusEntretien statusEntretien;

    @ManyToOne
    private Camion camion;
}
