package com.izorai.pfa.module1.DTO.partenaire.adress;

public record AdressDTO(
        Long idAdress,
        String ville,
        String pays,
        String rue,
        String codePostal

) {
}
