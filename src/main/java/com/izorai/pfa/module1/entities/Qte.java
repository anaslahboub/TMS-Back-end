package com.izorai.pfa.module1.entities;

import com.izorai.pfa.module1.entities.marchandises.Marchandise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qte {
    @EmbeddedId
    private QteId id;

    private int quantite;

    @ManyToOne
    private Voyage voyage;

    @ManyToOne
    private Marchandise marchandise;
}
