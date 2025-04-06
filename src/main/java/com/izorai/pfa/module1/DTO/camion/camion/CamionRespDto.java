package com.izorai.pfa.module1.DTO.camion.camion;

import com.izorai.pfa.module1.DTO.camion.carburant.CarburantDTO;
import com.izorai.pfa.module1.DTO.camion.carburant.CarburantViewDto;
import com.izorai.pfa.module1.DTO.camion.entretien.EntretienViewResp;
import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.entities.enumerations.StatusCamion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CamionRespDto {
    private String immatriculation;
    private int poidsMax;
    private StatusCamion status;
    private CarteGrise carteGrise;
    private Assurance assurance;
    private List<EntretienViewResp> entretiens;
    private List<CarburantViewDto> carburants;
    private TypeCamion typeCamion;
}
