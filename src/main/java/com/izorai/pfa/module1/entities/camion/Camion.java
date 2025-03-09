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

    @OneToOne(cascade = CascadeType.ALL)
    private CarteGrise carteGrise;
    @OneToOne(cascade = CascadeType.ALL)
    private Assurance assurance;

    @OneToMany(mappedBy = "camion", cascade = CascadeType.ALL)
    private List<Entretien> entretiens;
    @OneToMany(mappedBy = "camion", cascade = CascadeType.ALL)
    private List<Carburant> carburants;


}
