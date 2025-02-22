package com.izorai.pfa.module1.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remorque  extends Camion {
    private String typeRemorque;
    private int volumesStockage;
    private int poidsVide;
    private int poidsChargeMax;
}
