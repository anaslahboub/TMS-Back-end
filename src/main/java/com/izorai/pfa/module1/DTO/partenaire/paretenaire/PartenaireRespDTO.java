package com.izorai.pfa.module1.DTO.partenaire.paretenaire;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartenaireRespDTO {
    private Long idPartenaire;
    private String nom;
    private String email;
    private String telephone;
    private List<Adress> adresses;

}
