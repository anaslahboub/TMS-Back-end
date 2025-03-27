package com.izorai.pfa.module2.DTO.marchandises;

public record MarchandiseDTO(
        int id,
        String libelle,
        String description,
        String codeMarchandise,
        CategorieDTO categorie) {}
