package com.izorai.pfa.module2.entities.contient;

import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.entities.marchandises.Emballage;
import com.izorai.pfa.module2.entities.marchandises.Marchandise;
import com.izorai.pfa.module2.entities.marchandises.Unite;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContientId implements Serializable {
    @ManyToOne
    private Voyage voyage;

    @ManyToOne
    private Unite unite;

    @ManyToOne
    private Marchandise marchandise;

    @ManyToOne
    private Emballage emballage;




}