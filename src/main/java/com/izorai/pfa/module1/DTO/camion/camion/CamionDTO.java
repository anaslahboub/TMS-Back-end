package com.izorai.pfa.module1.DTO.camion.camion;
import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.entities.camion.Assurance;
import com.izorai.pfa.module1.entities.camion.CarteGrise;


public record CamionDTO(
        String immatriculation,
        String typeCabine,
        int poidsMax,
        int consommation,
        Assurance assurance,
        CarteGrise carteGrise
        ) {}









//import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
//
//import lombok.*;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CamionDTO {
//    private Long immatriculation;
//    private CarteGriseDTO carteGrise;
//    private AssuranceDTO assurance;
//}
