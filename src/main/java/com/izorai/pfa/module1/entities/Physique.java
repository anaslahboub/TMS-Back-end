package com.izorai.pfa.module1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Physique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String CNI;
    private String prenom;

}
