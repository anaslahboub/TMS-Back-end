package com.izorai.pfa.module1.DTO.paretenaire.chaufeur;


import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.util.List;

public record ChaufeurRespDTO(
        Long idPartenaire,
        String nom,
        String CNI,
        String prenom,
        String cnss,
        String disponibilite,
        List<Adress> adresses
) {
}
