package com.izorai.pfa.module1.DTO.partenaire.chaufeur;


import com.izorai.pfa.module1.entities.partenaire.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaufeurRespDTO{
        Long idPartenaire;
        String nom;
        String email;
        String telephone;
        String cni;
        String prenom;
        String cnss;
        LocalDate dateExpirationPermis;
        LocalDate dateRecrutement;
        String disponibilite;
        List<Adress> adresses;


}
