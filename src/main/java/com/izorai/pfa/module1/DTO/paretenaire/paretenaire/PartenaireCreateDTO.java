package com.izorai.pfa.module1.DTO.paretenaire.paretenaire;

import com.izorai.pfa.module1.entities.partenaire.Adress;

import java.util.List;

public record PartenaireCreateDTO(
        String nom,
        String email,
        String telephone,
        List<Adress> adresses) {
}
