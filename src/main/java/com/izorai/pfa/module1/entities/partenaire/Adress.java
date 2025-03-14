package com.izorai.pfa.module1.entities.partenaire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Adress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdress;
    private String type;
    private String rue;
    private String ville;
    private String pays;
    private String codePostal;


    @Override
    public String toString() {
        return "Adress{" +
                "idAdress=" + idAdress +
                ", type='" + type + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", codePostal='" + codePostal + '\'' +
                '}';
    }

}
