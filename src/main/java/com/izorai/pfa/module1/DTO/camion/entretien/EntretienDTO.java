package com.izorai.pfa.module1.DTO.camion.entretien;

import com.izorai.pfa.module1.DTO.camion.camion.CamionDTO;
import com.izorai.pfa.module1.entities.camion.Camion;
import java.time.LocalDate;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class EntretienDTO {
//    private Long id;
//    private LocalDate dateEntretien;
//    private String typeEntretien;
//    private String description;
//    private int cout;
//    private LocalDate dateProchainEntretien;
//    private Camion camion;
//}


public record EntretienDTO(
        Long id,
        LocalDate dateEntretien,
        String typeEntretien,
        String description,
        int cout,
        LocalDate dateProchainEntretien,
        CamionDTO camion
) {}
