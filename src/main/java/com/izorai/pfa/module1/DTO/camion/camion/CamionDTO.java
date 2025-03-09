package com.izorai.pfa.module1.DTO.camion.camion;
import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;



public record CamionDTO(
        String immatriculation,
        String typeCabine,
        int poidsMax,
        int consommation,
        CarteGriseDTO carteGrise,
        AssuranceDTO assurance
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
