package com.izorai.pfa.module2.entities.warning;

import com.izorai.pfa.module2.entities.Voyage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoyageWarning implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private String warningType; // "ASSURANCE", "CARTE_GRISE", "PERMIS"
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;
}