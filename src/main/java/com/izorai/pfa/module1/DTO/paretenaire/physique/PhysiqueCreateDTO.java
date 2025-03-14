package com.izorai.pfa.module1.DTO.paretenaire.physique;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysiqueCreateDTO {
    private String nom;
    private String email;
    private String telephone;
    private String CNI;
    private String prenom;
}
