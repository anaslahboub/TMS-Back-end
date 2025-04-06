package com.izorai.pfa.module1.entities.camion;


import com.izorai.pfa.module1.entities.enumerations.StatusCamion;
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
    private int poidsMax;

    @Enumerated(EnumType.STRING)
    private StatusCamion status;

    @OneToOne
    private CarteGrise carteGrise;
    @OneToOne
    private Assurance assurance;

    @OneToMany(mappedBy = "camion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entretien> entretiens;
    @OneToMany(mappedBy ="camion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carburant> carburants;
    @ManyToOne
    TypeCamion typeCamion;

}