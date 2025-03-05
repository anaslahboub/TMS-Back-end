package com.izorai.pfa.module1.DTO.paretenaire.Morale;

public record MoraleCreateDTO(
        String nom,
        String email,
        String telephone,
        int ICE,
        Long numeroRC,
        String abreviation,
        String formeJuridique) {
}
