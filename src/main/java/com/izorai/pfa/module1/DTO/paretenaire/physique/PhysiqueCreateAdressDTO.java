package com.izorai.pfa.module1.DTO.paretenaire.physique;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhysiqueCreateAdressDTO  {
    private String nom;
    private String email;
    private String telephone;
    private String CNI;
    private String prenom;
    private List<Adress> adresses;


}
