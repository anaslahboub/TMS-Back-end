package com.izorai.pfa.module1.DTO.partenaire.Morale;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoraleRespDTO {
        Long idPartenaire;
        String nom;
        String email;
        String telephone;
        Long ice;
        Long numeroRC;
        String abreviation;
        String formeJuridique;
        List<Adress> adresses;
        TypePartenaire typePartenaire;
}
