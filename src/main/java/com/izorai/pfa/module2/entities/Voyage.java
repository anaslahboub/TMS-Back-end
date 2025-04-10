package com.izorai.pfa.module2.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Chaufeur;
import com.izorai.pfa.module1.entities.camion.Remorque;
import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module2.entities.contient.Contient;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.enumerations.EtatVoyage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Voyage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDepart;
    private LocalDate dateArrivePrevue;
    private LocalDate dateArriveReelle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lieu_depart_id_adress")
    private Adress lieuDepart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lieu_arrive_id_adress")
    private Adress lieuArrive;
    private int distance;

    @Enumerated(EnumType.STRING)
    private EtatVoyage etat;
    private boolean estUrgent;
    private boolean estFragile;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Chaufeur chaufeur;

    @ManyToOne
    private Remorque remorque;

    @ManyToOne
    private Camion camion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contient> listMarchandises = new ArrayList<>();

}
