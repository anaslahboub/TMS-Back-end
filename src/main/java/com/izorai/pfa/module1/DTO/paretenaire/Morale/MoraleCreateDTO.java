package com.izorai.pfa.module1.DTO.paretenaire.Morale;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;

import java.util.List;

public record MoraleCreateDTO(
        String nom,
        String email,
        String telephone,
        int ICE,
        Long numeroRC,
        String abreviation,
        String formeJuridique,
        List<Adress> adresses,
        List<TypePartenaire> typePartenaires
        ) {

}
