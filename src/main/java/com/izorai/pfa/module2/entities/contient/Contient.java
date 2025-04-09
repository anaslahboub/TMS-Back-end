package com.izorai.pfa.module2.entities.contient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.marchandises.Emballage;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.entities.marchandises.Unite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Contient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long quantite;

    @ManyToOne
    private Marchandise marchandise;
    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;

}