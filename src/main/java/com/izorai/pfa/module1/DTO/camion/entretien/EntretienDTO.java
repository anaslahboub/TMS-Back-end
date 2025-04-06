package com.izorai.pfa.module1.DTO.camion.entretien;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.enumerations.StatusEntretien;
import com.izorai.pfa.module1.entities.enumerations.TypeEntretien;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntretienDTO {
    private Long id;
    private LocalDate dateEntretien;
    private TypeEntretien typeEntretien;
    private String description;
    private int cout;
    private StatusEntretien statusEntretien;
    private LocalDate dateProchainEntretien;
    private Camion camion;
}


