package com.izorai.pfa.module1.DTO.partenaire.physique;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysiqueRespDTO{
        Long idPartenaire;
        String nom;
        String email;
        String telephone;
        String cni;
        String prenom;
        List<Adress> adresses;
        TypePartenaire typePartenaire;
}
