package com.izorai.pfa.module1.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Truck extends Camion {
    private String typeCabine;
    private int poidsMax;
    private int consommation;

}
