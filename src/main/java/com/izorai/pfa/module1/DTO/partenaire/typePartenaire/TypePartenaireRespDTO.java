package com.izorai.pfa.module1.DTO.partenaire.typePartenaire;

public record TypePartenaireRespDTO(
        Long idTypePartenaire,
        String libelle,
        String definition,
        String genre
) {
}
