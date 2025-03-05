package com.izorai.pfa.module1.entities.camion;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remorque  extends Camion implements Serializable {
    private String typeRemorque;
    private int volumesStockage;
    private int poidsVide;
    private int poidsChargeMax;
}
