package com.izorai.pfa.module2.entities;

import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"voyage_id", "marchandise_id"})
})
public class Qte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;

    @ManyToOne
    @JoinColumn(name = "marchandise_id")
    private Marchandise marchandise;
}
