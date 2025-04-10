package com.izorai.pfa.module1.entities.camion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remorque  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRemorque;
    @Column(nullable = false)
    private int volumeStockage;
    @Column(nullable = false)
    private int poidsVide;
    @Column(nullable = false)
    private int poidsChargeMax;
    private boolean disponible;
    @ManyToOne
    private TypeRemorque typeRemorque;
}
