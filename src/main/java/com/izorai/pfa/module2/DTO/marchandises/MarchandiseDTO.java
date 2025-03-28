package com.izorai.pfa.module2.DTO.marchandises;

public record MarchandiseDTO(
        Long id,
        String libelle,
        String description,
        String codeMarchandise,
        CategorieDTO categorie) {}
