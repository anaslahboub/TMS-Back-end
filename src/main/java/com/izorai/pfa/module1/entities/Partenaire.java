package com.izorai.pfa.module1.entities;

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

public class Partenaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartenaire;
    private String nom;
    private String email;
    private int telephone;
    @OneToMany
    private List<Adress> adresses;
    @OneToMany
    private List<TypePartenaire> typePartenaires;

}
