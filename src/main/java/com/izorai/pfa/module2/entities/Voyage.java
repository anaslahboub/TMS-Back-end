package com.izorai.pfa.module2.entities;

import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
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
public class Voyage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDepart;
    private LocalDate dateArrivePrevue;
    private LocalDate dateArriveReelle;
    private String lieuDepart;
    private String lieuArrive;
    private int distance;
    private String etat;

    @ManyToOne
    private Chaufeur chaufeur;
    @ManyToOne
    private Remorque remorque;


}
