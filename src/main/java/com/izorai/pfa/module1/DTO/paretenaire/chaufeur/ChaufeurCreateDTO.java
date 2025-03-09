package com.izorai.pfa.module1.DTO.paretenaire.chaufeur;

import java.time.LocalDate;

public record ChaufeurCreateDTO(
        String nom,
        String email,
        String telephone,
        String CNI,
        String prenom,
        String cnss,
        LocalDate dateRecrutement,
        String disponibilite

) {
}
