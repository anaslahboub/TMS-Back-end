package com.izorai.pfa.module1.DTO.camion.carburant;

import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Station;
import com.izorai.pfa.module1.entities.camion.TypeCarburant;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarburantDTO {
    private Long id;
    private LocalDate dateRemplissage;
    private int quantiteLitres;
    private int prixParLitre;
    private int kilometrageActuel;
    private TypeCarburant typeCarburant;
    private Camion camion;
    private Station station;
    private byte[] photoCarburant;

}
