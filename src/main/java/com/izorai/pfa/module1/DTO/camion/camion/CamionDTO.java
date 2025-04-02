package com.izorai.pfa.module1.DTO.camion.camion;
import com.izorai.pfa.module1.DTO.camion.assurance.AssuranceDTO;
import com.izorai.pfa.module1.DTO.camion.cartegrise.CarteGriseDTO;
import com.izorai.pfa.module1.DTO.camion.typeCamion.TypeCamionDTO;
import com.izorai.pfa.module1.entities.enumerations.StatusCamion;
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
        StatusCamion status;
}
