package com.izorai.pfa.module1.DTO.camion.carburant;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.camion.Station;
import com.izorai.pfa.module1.entities.camion.TypeCarburant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarburantRespDto {
    private Long id;
    private LocalDate dateRemplissage;
    private int quantiteLitres;
    private int prixParLitre;
    private int kilometrageActuel;
    private TypeCarburant typeCarburant;
    private int montantActuel;
    private int consommation;
    private byte[] photoCarburant;
    private Station station;
    private CamionDTO camion;

}
