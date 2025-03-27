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
    private String libelle;
    private String description;
    private String codeMarchandise; // Code unique pour la marchandise

    @ManyToOne
    private Categorie categorie;
}
