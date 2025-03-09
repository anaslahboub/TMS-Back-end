package com.izorai.pfa.module1.entities.partenaire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass

public class Partenaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPartenaire;
    private String nom;
    private String email;
    private String telephone;
    @OneToMany
    private List<Adress> adresses;
    @OneToMany
    private List<TypePartenaire> typePartenaires;

}
