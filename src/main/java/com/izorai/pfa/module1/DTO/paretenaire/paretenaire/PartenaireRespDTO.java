package com.izorai.pfa.module1.DTO.paretenaire.paretenaire;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import com.izorai.pfa.module1.entities.partenaire.TypePartenaire;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
