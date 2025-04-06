package com.izorai.pfa.module2.DTO.marchandises;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieDTO{
    Long id;
    String libelle;
    String description;

}

