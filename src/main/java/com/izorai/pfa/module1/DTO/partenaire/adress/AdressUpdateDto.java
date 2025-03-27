package com.izorai.pfa.module1.DTO.partenaire.adress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressUpdateDto {
    private String rue;
    private String ville;
    private String pays;
    private String codePostal;
}
