package com.izorai.pfa.module1.entities.camion;

import com.izorai.pfa.module1.entities.enumerations.ResultatVisite;
import com.izorai.pfa.module1.entities.enumerations.TypeControle;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisiteTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateVisite;
    @Column(nullable = false)
    private LocalDate dateExpiration;
    @Enumerated(EnumType.STRING)
    private ResultatVisite resultatVisite;
    private String observations;
    private String documentUrl;
    @ManyToOne
    @JoinColumn(name = "immatriculation", nullable = false)
    private Camion camion;



//    @ElementCollection
//    @MapKeyEnumerated(EnumType.STRING)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "visite_technique_controle_results")
//    private Map<TypeControle, ResultatVisite> typeControleResultatVisite;

}