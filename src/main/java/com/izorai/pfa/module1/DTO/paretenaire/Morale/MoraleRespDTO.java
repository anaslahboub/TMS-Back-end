package com.izorai.pfa.module1.DTO.paretenaire.Morale;

public record MoraleRespDTO(
        Long idPartenaire,
        String nom,
        String email,
        String telephone,
        int ICE,
        Long numeroRC,
        String abreviation,
        String formeJuridique
) {
}
