package com.izorai.pfa.module1.DTO.paretenaire.adress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressCreateDto {
    private String type;
    private String rue;
    private String ville;
    private String pays;
    private String codePostal;
}
