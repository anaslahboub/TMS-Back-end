package com.izorai.pfa.module1.DTO.paretenaire.chaufeur;

import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.time.LocalDate;
import java.util.List;

public record ChaufeurCreateDTO(
        String nom,
        String email,
        String telephone,
        String CNI,
        String prenom,
        String cnss,
        LocalDate dateRecrutement,
        String disponibilite,
        List<Adress> adresses

) {
}
