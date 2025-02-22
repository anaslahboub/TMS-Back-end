package com.izorai.pfa.module1.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class QteId implements Serializable {
    private Long idVoyage;
    private Long idMarchandise;
}
