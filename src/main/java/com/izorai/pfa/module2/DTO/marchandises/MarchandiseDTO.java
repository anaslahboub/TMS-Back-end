package com.izorai.pfa.module2.DTO.marchandises;

import com.izorai.pfa.module2.entities.marchandises.Categorie;
import com.izorai.pfa.module2.entities.marchandises.Emballage;
import com.izorai.pfa.module2.entities.marchandises.Unite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarchandiseDTO {
        Long  id;
        String libelle;
        String description;
        String codeMarchandise;
        Categorie categorie;
        Unite unite;
        Emballage emballage;
}
