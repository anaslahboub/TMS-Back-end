package com.izorai.pfa.module1.entities.partenaire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(callSuper = false)

public class Morale extends Partenaire {
    private int ICE;
    private Long numeroRC;
    private String abreviation;
    private String formeJuridique;


}
