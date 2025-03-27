package com.izorai.pfa.module2.entities.contient;

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
public class Contient {
    @EmbeddedId
    private ContientId id;

    private long qte;


}