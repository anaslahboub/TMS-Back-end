package com.izorai.pfa.module1.DTO.camion.visiteTechnique;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.enumerations.ResultatVisite;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@Data
@RequiredArgsConstructor
public class VisiteTechniqueDto {
    private Long id;
    private String centreVisite;
    private LocalDate dateVisite;
    private LocalDate dateExpiration;
    private ResultatVisite resultatVisite;
    private String observations;
    private String documentUrl;
    private CamionDTO camion;
}
