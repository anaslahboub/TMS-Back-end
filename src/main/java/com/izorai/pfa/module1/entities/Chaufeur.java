package com.izorai.pfa.module1.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chaufeur extends Physique implements Serializable {
    private String cnss;
    private LocalDate dateRecrutement;
    private String disponnibilite;
}
