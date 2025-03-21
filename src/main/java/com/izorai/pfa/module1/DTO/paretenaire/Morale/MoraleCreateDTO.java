package com.izorai.pfa.module1.DTO.paretenaire.Morale;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoraleCreateDTO{
        String nom;
        String email;
        String telephone;
        Long ice;
        Long numeroRC;
        String abreviation;
        String formeJuridique;
        List<Adress> adresses;
        TypePartenaire typePartenaire;  // Utiliser l'ID au lieu de l'objet complet

}
