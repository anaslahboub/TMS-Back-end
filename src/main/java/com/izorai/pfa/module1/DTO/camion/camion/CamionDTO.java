package com.izorai.pfa.module1.DTO.camion.camion;
import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;


public record CamionDTO(
        String immatriculation,
        int poidsMax,
        int consommation,
        AssuranceDTO assurance,
        CarteGriseDTO carteGrise,
        TypeCamionDTO typeCamion
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
