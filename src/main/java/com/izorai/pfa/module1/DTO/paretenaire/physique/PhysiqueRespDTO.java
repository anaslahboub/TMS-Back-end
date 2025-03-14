package com.izorai.pfa.module1.DTO.paretenaire.physique;

import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.util.List;

public record PhysiqueRespDTO(
        Long idPartenaire,
        String nom,
        String email,
        String telephone,
        String CNI,
        String prenom,
        List<Adress> adresses

) {
}
