package com.izorai.pfa.module1.entities.camion;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Camion implements Serializable {
    @Id
    private String immatriculation;
    private String typeCabine;
    private int poidsMax;
    private int consommation;

    private boolean disponible;

    @OneToOne
    private CarteGrise carteGrise;
    @OneToOne
    private Assurance assurance;

    @OneToMany(mappedBy = "camion")
    private List<Entretien> entretiens;
    @OneToMany(mappedBy = "camion")
    private List<Carburant> carburants;

}
