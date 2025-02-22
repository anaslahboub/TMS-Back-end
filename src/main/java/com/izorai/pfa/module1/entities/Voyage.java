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
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateDepart;
    private LocalDate dateArrivePrevue;
    private LocalDate dateArriveRelle;
    private String lieuDepart;
    private String lieuArrive;
    private int distance;
    private String etat;

    @ManyToOne
    private Chaufeur chaufeur;
    @ManyToOne
    private Remorque remorque;
    @ManyToOne
    private Truck truck;



}
