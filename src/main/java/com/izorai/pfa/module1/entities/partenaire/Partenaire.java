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
    @Column(nullable = false)
    private String nom;
    private String email;
    @Column(nullable = false)
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adress> adresses;
    @ManyToOne
    private TypePartenaire typePartenaire;

}
