package com.izorai.pfa.module1.DTO.partenaire.chaufeur;

import com.izorai.pfa.module1.entities.partenaire.Adress;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaufeurVoyageDTO{
    Long idPartenaire;
    String nom;
    String cni;
    String prenom;
    LocalDate dateExpirationPermis;



}
