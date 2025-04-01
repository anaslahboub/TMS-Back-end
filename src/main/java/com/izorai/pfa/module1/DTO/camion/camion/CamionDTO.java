package com.izorai.pfa.module1.DTO.camion.camion;
import com.izorai.pfa.module1.DTO.camion.remorque.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamionDTO {
        String immatriculation;
        int poidsMax;
        AssuranceDTO assurance;
        CarteGriseDTO carteGrise;
        TypeCamionDTO typeCamion;
}
