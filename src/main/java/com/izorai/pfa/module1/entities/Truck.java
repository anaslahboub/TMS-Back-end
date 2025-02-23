package com.izorai.pfa.module1.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Truck extends Camion implements Serializable {
    private String typeCabine;
    private int poidsMax;
    private int consommation;

}
