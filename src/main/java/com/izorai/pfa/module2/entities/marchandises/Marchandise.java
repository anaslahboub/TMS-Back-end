package com.izorai.pfa.module2.entities.marchandises;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Marchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String libelle;
    private String description;
    @Column(nullable = false)
    private String codeMarchandise;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private Unite unite;
    @ManyToOne
    private Emballage emballage;
}
