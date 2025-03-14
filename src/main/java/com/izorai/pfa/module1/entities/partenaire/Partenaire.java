package com.izorai.pfa.module1.entities.partenaire;

import jakarta.annotation.Nullable;
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
    @OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Adress> adresses;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TypePartenaire> typePartenaires;

}
