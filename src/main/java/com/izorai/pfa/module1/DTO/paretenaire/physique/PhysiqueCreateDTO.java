package com.izorai.pfa.module1.DTO.paretenaire.physique;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysiqueCreateDTO {
    private String nom;
    private String email;
    private String telephone;
    private String cni;
    private String prenom;
    private List<Adress> adresses;
    private TypePartenaire typePartenaire;

}
