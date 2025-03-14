package com.izorai.pfa.module1.DTO.camion.remorque;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemorqueCreateDto {
   private String typeRemorque;
   private int volumesStockage;
   private int poidsVide;
   private int poidsChargeMax;
}
