package com.izorai.pfa.module1.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Camion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long immatriculation;
    @OneToOne(cascade = CascadeType.ALL)
    private CarteGrise carteGrise;
    @OneToOne(cascade = CascadeType.ALL)
    private Assurance assurance;

}
