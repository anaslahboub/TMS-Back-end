package com.izorai.pfa.module1.DTO.camion.carburant;


import com.izorai.pfa.module1.entities.camion.Camion;
import com.izorai.pfa.module1.entities.enumerations.TypeCarburant;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record CarburantDTO (
     Long id,
     LocalDate dateRemplissage,
     int quantity,
     int prixParLitre,
     int kilometrageActuel,
     TypeCarburant typeCarburant,
     String immatriculation){
}


//import com.izorai.pfa.module1.entities.camion.Camion;
//import jakarta.persistence.OneToOne;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class CarburantDTO {
//    private Long id;
//    private LocalDate dateRemplissage;
//    private int quantity;
//    private int prixParLitre;
//    private int kilometrageActuel;
//    private String typeCarburant;
//    private Camion camion;
//}
