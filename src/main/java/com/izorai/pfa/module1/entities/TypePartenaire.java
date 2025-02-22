package com.izorai.pfa.module1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypePartenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypePartenaire;
    private String libelle;
    private String definition;
    private String genre;
    @ManyToOne
    private Morale morale;

}
