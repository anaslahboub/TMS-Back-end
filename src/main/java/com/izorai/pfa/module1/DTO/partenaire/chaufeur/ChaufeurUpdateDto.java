package com.izorai.pfa.module1.DTO.partenaire.chaufeur;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaufeurUpdateDto {
    private Long idPartenaire;
    private String nom;
    private String CNI;
    private String email;
    private String telephone;
    private String prenom;
    private String cnss;
    private LocalDate dateRecrutement;
    private String disponibilite;
    private LocalDate dateExpirationPermis;

}
