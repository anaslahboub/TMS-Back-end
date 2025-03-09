package com.izorai.pfa.module1.entities.camion;

import com.izorai.pfa.module1.entities.partenaire.Physique;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class Chaufeur extends Physique implements Serializable {
    private String cnss;
    private LocalDate dateRecrutement;
    private String disponibilite;
}
